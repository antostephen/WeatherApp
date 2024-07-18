package com.anto.posts.domain.usecase

import androidx.lifecycle.LiveData
import com.anto.posts.data.repository.LocationsRepository
import com.anto.posts.domain.entities.Locations
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationsRepository
) {
    suspend fun addLocation(location: Locations) {
        return locationRepository.addLocation(location)
    }

    suspend fun deleteLocation(location: Locations) {
        locationRepository.deleteLocation(location)
    }

    fun getAllLocations(): LiveData<List<Locations>> {
        return locationRepository.getAllLocations()
    }
}
