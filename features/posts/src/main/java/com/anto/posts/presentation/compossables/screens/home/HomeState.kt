package com.anto.posts.presentation.compossables.screens.home

import com.anto.posts.domain.entities.WeatherEntity

data class HomeState(
    val isLoading: Boolean = false,
    val data: WeatherEntity? = null
)