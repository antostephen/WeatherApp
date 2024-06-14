package com.anto.weatherapp.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.anto.weatherapp.data.local.mappers.toLocations
import com.anto.weatherapp.data.local.mappers.toLocationsEntity
import com.anto.weatherapp.domain.model.Locations
import com.anto.weatherapp.domain.repository.LocationsRepository

class LocationsRepositoryImpl(
    private val dao: LocationsDao
) : LocationsRepository {
    override suspend fun addLocation(location: Locations) {
        dao.addLocation(location.toLocationsEntity())
    }

    override suspend fun deleteLocation(locations: Locations) {
        dao.deleteLocation(locations.toLocationsEntity())
    }

    override fun getAllLocations(): LiveData<List<Locations>> {
        return Transformations.map(dao.getAllLocations()) { entity ->
            entity.map {
                it.toLocations()
            }
        }
    }
}