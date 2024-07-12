package com.anto.posts

import androidx.lifecycle.LiveData
import com.anto.posts.data.repository.LocationsRepository
import com.anto.posts.domain.entities.Locations
import com.anto.posts.utils.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class LocationsRepositoryTest {

    @Mock
    private lateinit var mockLocationsLiveData: LiveData<List<Locations>>

    @Mock
    private lateinit var mockLocationsRepository: LocationsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test addLocation`() = runBlocking {
        val testLocation = Locations(Constants.LOCATION_QUERY)
        mockLocationsRepository.addLocation(testLocation)
    }

    @Test
    fun `test deleteLocation`() = runBlocking {
        val testLocation = Locations(Constants.LOCATION_QUERY)
        mockLocationsRepository.deleteLocation(testLocation)
    }

    @Test
    fun `test getAllLocations`() {
        `when`(mockLocationsRepository.getAllLocations()).thenReturn(mockLocationsLiveData)
        val result = mockLocationsRepository.getAllLocations()
        // Assert the result
        assertNotNull(result)
    }
}
