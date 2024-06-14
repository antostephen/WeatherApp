package com.anto.weatherapp.data.remote

import com.anto.weatherapp.BuildConfig
import com.anto.weatherapp.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("key") key : String = BuildConfig.API_KEY,
        @Query("days") days : Int = 3,
        @Query("aqi") aqi : String = "no",
        @Query("alerts") alerts : String = "yes",
    ) : WeatherResponse
}