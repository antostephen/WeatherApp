package com.anto.posts.presentation.compossables.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anto.posts.domain.entities.Locations
import com.anto.core.utils.Resource
import com.anto.posts.di.MainDispatcher
import com.anto.posts.domain.usecase.LocationUseCase
import com.anto.posts.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchWeatherUseCase: WeatherUseCase,
    private val fetchLocationUseCase: LocationUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    @Module
    @InstallIn(ViewModelComponent::class)
    object ViewModelModule {
        @Provides
        fun provideHomeViewModel(locationUseCase: LocationUseCase,
                                 weatherUseCase: WeatherUseCase, dispatcher: CoroutineDispatcher): HomeViewModel {
            return HomeViewModel(weatherUseCase, locationUseCase, dispatcher)
        }
    }

    private val _locationDialogValue = mutableStateOf("")
    val locationDialogValue: State<String> = _locationDialogValue

    private val _currentLocation = mutableStateOf("")
    val currentLocation: State<String> = _currentLocation

    val allLocations = fetchLocationUseCase.getAllLocations()

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private fun notifyCurrentLocation(): StateFlow<String?> = fetchWeatherUseCase.getCurrentLocationQuery()

    init {
        viewModelScope.launch {
            fetchWeatherUseCase.getCurrentLocationQuery().collect {
                _currentLocation.value = it.toString()
            }
        }
        viewModelScope.launch {
            notifyCurrentLocation().collect { location ->
                location?.let { getWeatherDetails(it) }
            }
        }
    }

    private fun getWeatherDetails(location: String) {
        viewModelScope.launch (dispatcher) {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = fetchWeatherUseCase.getWeatherData(location)
            println("WeatherReport : " + result.data.toString())
            when (result) {
                is Resource.Success<*> -> {
                    _state.value = state.value.copy(
                        data = result.data,
                        isLoading = false
                    )
                }

                is Resource.Error<*> -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                    )
                }

                else -> {}
            }

        }
    }

    fun saveToSharedPrefs(locationName: String) {
        fetchWeatherUseCase.saveLocation(locationName)
    }

    fun setLocationDialogValue(text: String) {
        _locationDialogValue.value = text
    }

    suspend fun deleteLocation(location: Locations) {
        fetchLocationUseCase.deleteLocation(location)
    }

    fun addLocation() {
        viewModelScope.launch(dispatcher) {
            if (locationDialogValue.value.isNotBlank()) {
                fetchLocationUseCase.addLocation(
                    Locations(
                        locationDialogValue.value
                    )
                )
            } else return@launch
        }
    }
}