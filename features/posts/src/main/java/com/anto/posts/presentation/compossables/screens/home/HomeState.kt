package com.anto.weatherapp.presentation.compossables.screens.home

import com.anto.weatherapp.data.response.WeatherResponse

data class HomeState(
    val isLoading : Boolean = false,
    val data: WeatherResponse? = null
)