package com.anto.posts.domain.usecase

import com.anto.core.utils.Resource
import com.anto.posts.domain.entities.WeatherEntity
import com.anto.posts.domain.repository.WeatherRepository

class WeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getWeatherData(location: String): Resource<WeatherEntity> {
        return weatherRepository.getWeatherData(location)
    }
}