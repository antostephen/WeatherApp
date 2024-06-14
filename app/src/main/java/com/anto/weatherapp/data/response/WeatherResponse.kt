package com.anto.weatherapp.data.response

import com.anto.weatherapp.data.models.Alerts
import com.anto.weatherapp.data.models.Current
import com.anto.weatherapp.data.models.Forecast
import com.anto.weatherapp.data.models.Location
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