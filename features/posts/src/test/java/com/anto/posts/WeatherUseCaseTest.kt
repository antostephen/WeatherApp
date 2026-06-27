package com.anto.posts

import com.anto.posts.domain.repository.WeatherRepository
import com.anto.posts.domain.usecase.WeatherUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.anto.posts.constants.Constants.LOCATION_QUERY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class WeatherUseCaseTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineRule()

    private lateinit var weatherUseCase: WeatherUseCase
    private val repository: WeatherRepository = mock()

    @Before
    fun setUp() {
        weatherUseCase = WeatherUseCase(repository)
    }

    @Test
    fun `test execute calls repository`() = runTest {
        // Given
        `when`(repository.getWeatherData(LOCATION_QUERY)).thenReturn(mock())

        // When
        weatherUseCase.getWeatherData(LOCATION_QUERY)

        // Then
        verify(repository).getWeatherData(LOCATION_QUERY)
    }
}

@ExperimentalCoroutinesApi
class MainCoroutineRule(val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: org.junit.runner.Description) {
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: org.junit.runner.Description) {
        Dispatchers.resetMain()
        cleanupTestCoroutines()
    }
}
