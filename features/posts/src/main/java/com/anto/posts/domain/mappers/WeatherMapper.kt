package com.anto.posts.domain.mappers;

import com.anto.posts.domain.response.WeatherResponse;
import com.anto.posts.domain.entities.WeatherEntity;

class WeatherMapper {
    fun mapToEntity(apiResponse: WeatherResponse): WeatherEntity{
        return WeatherEntity(
                location = apiResponse.location,
                current = apiResponse.current,
                forecast = apiResponse.forecast,
                alerts = apiResponse.alerts
        );
    }
}
