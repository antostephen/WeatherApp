package com.anto.posts.domain.repository

import android.content.SharedPreferences
import com.anto.posts.data.remote.ApiService
import com.anto.core.utils.Constants.LOCATION_QUERY
import com.anto.core.utils.Resource
import com.anto.posts.domain.entities.WeatherEntity
import com.anto.posts.domain.mappers.WeatherMapper
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences,
    private val weatherMapper: WeatherMapper

) {
    val currentLocationQuery = MutableStateFlow(
        sharedPreferences.getString(LOCATION_QUERY, "Bengaluru")
    )

    suspend fun getWeatherData(location: String): Resource<WeatherEntity> {
        return try {
            Resource.Success(
                weatherMapper.mapToEntity(apiService.getWeather(location))
            )
        } catch (e: IOException) {
            Resource.Error(message = "Error! ${e.message}")
        } catch (e: HttpException) {
            Resource.Error(
                message = "Error!${e.localizedMessage}"
            )
        }
    }

    fun saveToSharedPrefs(locationName: String) {
        sharedPreferences.edit().putString(LOCATION_QUERY, locationName).apply()
        currentLocationQuery.value = locationName
    }
}