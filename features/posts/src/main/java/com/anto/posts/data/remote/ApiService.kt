package com.anto.posts.data.remote

import com.anto.posts.BuildConfig
import com.anto.posts.domain.response.WeatherResponse
import com.anto.posts.constants.Constants.VALUE_NO
import com.anto.posts.constants.Constants.VALUE_YES
import com.anto.posts.constants.Constants.FORECAST_JSON_FILENAME
import com.anto.posts.constants.Constants.QUERY_Q
import com.anto.posts.constants.Constants.QUERY_KEY
import com.anto.posts.constants.Constants.QUERY_DAYS
import com.anto.posts.constants.Constants.QUERY_AQI
import com.anto.posts.constants.Constants.QUERY_ALERTS
import com.anto.posts.constants.Constants.THREE_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(FORECAST_JSON_FILENAME)
    suspend fun getWeather(
        @Query(QUERY_Q) query : String,
        @Query(QUERY_KEY) key : String = BuildConfig.API_KEY,
        @Query(QUERY_DAYS) days : Int = THREE_DAYS,
        @Query(QUERY_AQI) aqi : String = VALUE_NO,
        @Query(QUERY_ALERTS) alerts : String = VALUE_YES,
    ) : WeatherResponse
}