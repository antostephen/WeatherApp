package com.anto.posts.presentation.compossables.screens.home

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anto.posts.domain.repository.WeatherRepository
import com.anto.posts.domain.entities.Locations
import com.anto.posts.data.repository.LocationsRepository
import com.anto.core.utils.Resource
import com.anto.posts.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val repository: LocationsRepository,
    private val weatherUseCase: WeatherUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    @Module
    @InstallIn(ViewModelComponent::class)
    object ViewModelModule {
        @Provides
        fun provideHomeViewModel(weatherRepo: WeatherRepository, repository: LocationsRepository,
                                 weatherUseCase: WeatherUseCase, dispatcher: CoroutineDispatcher): HomeViewModel {
            return HomeViewModel(weatherRepo, repository, weatherUseCase, dispatcher)
        }
    }

    private val _locationDialogValue = mutableStateOf("")
    val locationDialogValue: State<String> = _locationDialogValue

    private val _currentLocation = mutableStateOf("")
    val currentLocation: State<String> = _currentLocation

    val allLocations = repository.getAllLocations()

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private fun notifyCurrentLocation(): StateFlow<String?> = weatherRepo.currentLocationQuery


    init {
        viewModelScope.launch {
            weatherRepo.currentLocationQuery.collect {
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
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = weatherUseCase.getWeatherData(location)
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
        weatherRepo.saveToSharedPrefs(locationName)
    }

    fun setLocationDialogValue(text: String) {
        _locationDialogValue.value = text
    }

    suspend fun deleteLocation(location: Locations) {
        repository.deleteLocation(location)
    }

    fun addLocation() {
        viewModelScope.launch {
            if (locationDialogValue.value.isNotBlank()) {
                repository.addLocation(
                    Locations(
                        locationDialogValue.value
                    )
                )
            } else return@launch
        }
    }
}