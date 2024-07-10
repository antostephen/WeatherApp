package com.anto.posts.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.anto.posts.data.database.LocationsDao
import com.anto.posts.data.local.mappers.toLocations
import com.anto.posts.data.local.mappers.toLocationsEntity
import com.anto.posts.domain.entity.Locations

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