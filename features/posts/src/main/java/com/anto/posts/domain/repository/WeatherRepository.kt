package com.anto.posts.domain.repository

import com.anto.core.utils.Resource
import com.anto.posts.domain.entities.WeatherEntity
import kotlinx.coroutines.flow.MutableStateFlow

interface WeatherRepository{
    val currentLocationQuery: MutableStateFlow<String?>
    suspend fun getWeatherData(location: String): Resource<WeatherEntity>
    fun saveToSharedPrefs(locationName: String)
}