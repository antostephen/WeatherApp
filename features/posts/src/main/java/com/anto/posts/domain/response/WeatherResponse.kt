package com.anto.posts.domain.response

import com.anto.posts.data.models.Alerts
import com.anto.posts.data.models.Current
import com.anto.posts.data.models.Forecast
import com.anto.posts.data.models.Location
import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("location")
    var location: Location,
    @SerializedName("current")
    var current: Current? = Current(),
    @SerializedName("forecast")
    var forecast: Forecast? = Forecast(),
    @SerializedName("alerts")
    var alerts: Alerts? = Alerts()

)