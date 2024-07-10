package com.anto.posts.presentation.compossables.screens.home

import com.anto.posts.domain.response.WeatherResponse

data class HomeState(
    val isLoading : Boolean = false,
    val data: WeatherResponse? = null
)