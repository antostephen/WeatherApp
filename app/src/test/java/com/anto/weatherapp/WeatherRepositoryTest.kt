package com.anto.weatherapp

import android.content.SharedPreferences
import com.anto.weatherapp.data.models.Alerts
import com.anto.weatherapp.data.models.Condition
import com.anto.weatherapp.data.models.Current
import com.anto.weatherapp.data.models.Forecast
import com.anto.weatherapp.data.models.Location
import com.anto.weatherapp.data.remote.ApiService
import com.anto.weatherapp.data.repository.WeatherRepository
import com.anto.weatherapp.data.response.WeatherResponse
import com.anto.weatherapp.utils.Constants.LOCATION_QUERY
import com.anto.weatherapp.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class WeatherRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var weatherRepository: WeatherRepository
    private val weatherResponse = WeatherResponse(
        Location(
            name = "Bengaluru",
            region = "Karnataka",
            country = "India",
            lat = 12.98,
            lon = 77.58,
            tzId = "Asia/Kolkata",
            localtimeEpoch = 1718367840,
            localtime = "2024-06-14 17:54"
        ), Current(
            lastUpdatedEpoch = 1718367300,
            lastUpdated = "2024-06-14 17:45",
            tempC = 29.1,
            tempF = 84.4,
            isDay = 1.0,
            Condition(
                text = "Partly cloudy",
                icon = "//cdn.weatherapi.com/weather/64x64/day/116.png, code=1003"
            ),
            windMph = 13.6,
            windKph = 22.0,
            windDegree = 250.0,
            windDir = "WSW",
            pressureMb = 1011.0,
            pressureIn = 29.85,
            precipMm = 0.0,
            precipIn = 0.0,
            humidity = 62.0,
            cloud = 50.0,
            feelslikeC = 30.5,
            feelslikeF = 86.9,
            visKm = 6.0,
            visMiles = 3.0,
            uv = 7.0,
            gustMph = 17.5,
            gustKph = 28.1
        ), Forecast(), Alerts()
    )

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
        sharedPreferences = mock(SharedPreferences::class.java)
        `when`(sharedPreferences.getString(LOCATION_QUERY, "Bengaluru")).thenReturn("Bengaluru")

        weatherRepository = WeatherRepository(apiService, sharedPreferences)
    }

    @Test
    fun `getWeatherData returns success when api call is successful`() = runTest {

        `when`(apiService.getWeather("Bengaluru")).thenReturn(weatherResponse)

        val result = weatherRepository.getWeatherData("Bengaluru")

        assertTrue(result is Resource.Success)
        assertEquals(weatherResponse, (result as Resource.Success).data)
    }

    @Test
    fun `getWeatherData returns error when IOException is thrown`() = runTest {
        val ioException = IOException("Network Error")
        `when`(apiService.getWeather("Bengaluru")).thenAnswer {
            throw ioException
        }
        val result = weatherRepository.getWeatherData("Bengaluru")
        assertTrue(result is Resource.Error)
        assertEquals("Error! Network Error", (result as Resource.Error).message)
    }

    @Test
    fun `getWeatherData returns error when HttpException is thrown`() = runTest {
        val responseBody = ResponseBody.create("application/json".toMediaTypeOrNull(), "{}")
        val response = Response.error<WeatherResponse>(404, responseBody)
        val httpException = HttpException(response)
        `when`(apiService.getWeather("Bengaluru")).thenThrow(httpException)
        val result = weatherRepository.getWeatherData("Bengaluru")
        assertTrue(result is Resource.Error)
        assertTrue((result as Resource.Error).message!!.startsWith("Error!"))
    }

    @Test
    fun `saveToSharedPrefs updates shared preferences and current location query`() {
        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putString(LOCATION_QUERY, "New York")).thenReturn(editor)
        weatherRepository.saveToSharedPrefs("New York")
        verify(editor).putString(LOCATION_QUERY, "New York")
        verify(editor).apply()
        assertEquals("New York", weatherRepository.currentLocationQuery.value)
    }

    @Test
    fun `currentLocationQuery returns initial value from shared preferences`() = runBlocking {
        val initialValue = weatherRepository.currentLocationQuery.first()
        assertEquals("Bengaluru", initialValue)
    }
}
