package com.anto.posts.domain.usecase

import com.anto.core.utils.Resource
import com.anto.posts.domain.entities.WeatherEntity
import com.anto.posts.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeatherData(location: String): Resource<WeatherEntity> {
        return weatherRepository.getWeatherData(location)
    }

    fun getCurrentLocationQuery() : MutableStateFlow<String?> {
        return weatherRepository.currentLocationQuery
    }

    fun saveLocation(locationName: String) {
        weatherRepository.saveToSharedPrefs(locationName)
    }
}


