package com.anto.posts.presentation.compossables.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anto.posts.presentation.compossables.screens.home.HomeState
import com.anto.core.utils.Resource
import com.anto.posts.di.MainDispatcher
import com.anto.posts.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchWeatherUseCase: WeatherUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> = _query

    fun setSearchQuery(location: String) {
        _query.value = location
    }

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state


    fun search(location: String) {
        _query.value = location

        viewModelScope.launch(dispatcher) {
            _state.value = state.value.copy(
                isLoading = true
            )
            when (val result = fetchWeatherUseCase.getWeatherData(_query.value)) {
                is Resource.Success<*> -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        data = result.data
                    )
                }

                is Resource.Error<*> -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                    )
                }

                else -> {

                }
            }
        }
    }

}