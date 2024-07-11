package com.anto.posts.domain.entities

import com.anto.posts.data.models.*

data class WeatherEntity(
    var location: Location,
    var current: Current? = Current(),
    var forecast: Forecast? = Forecast(),
    var alerts: Alerts? = Alerts()
)