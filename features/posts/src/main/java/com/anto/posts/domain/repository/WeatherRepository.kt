package com.anto.posts.domain.repository

import android.content.SharedPreferences
import com.anto.posts.domain.response.WeatherResponse
import com.anto.posts.data.remote.ApiService
import com.anto.core.utils.Constants.LOCATION_QUERY
import com.anto.core.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences

) {
    val currentLocationQuery = MutableStateFlow(
        sharedPreferences.getString(LOCATION_QUERY, "Bengaluru")
    )

    suspend fun getWeatherData(location: String): Resource<WeatherResponse> {
        return try {
            Resource.Success(
                data = api.getWeather(location)
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