package com.anto.posts

import androidx.lifecycle.LiveData
import com.anto.posts.data.repository.LocationsRepository
import com.anto.posts.domain.entities.Locations
import com.anto.posts.domain.usecase.LocationUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class LocationUseCaseTest {

    @get:Rule
    val coroutineTestRule = MainCoroutineRule()

    private lateinit var locationUseCase: LocationUseCase
    private val repository: LocationsRepository = mock(LocationsRepository::class.java)
    @Mock
    private lateinit var mockLocationsLiveData: LiveData<List<Locations>>

    @Before
    fun setUp() {
        locationUseCase = LocationUseCase(repository)
    }

    @Test
    fun `test execute calls repository`() = runTest {
        // Given
        `when`(locationUseCase.getAllLocations()).thenReturn(mockLocationsLiveData)

        // Then
        verify(repository).getAllLocations()
    }
}

