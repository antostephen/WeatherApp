package com.anto.core.domain.usecase

import com.anto.core.utils.Resource
import com.anto.posts.domain.repository.WeatherRepository
import com.anto.posts.domain.response.WeatherResponse

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(location: String): Resource<WeatherResponse> {
        return weatherRepository.getWeatherData(location)
    }
}