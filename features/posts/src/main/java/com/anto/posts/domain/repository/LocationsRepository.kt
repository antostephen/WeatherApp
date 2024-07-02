package com.anto.posts.domain.repository

import androidx.lifecycle.LiveData
import com.anto.posts.domain.entity.Locations

interface LocationsRepository {


    suspend fun addLocation(location: Locations)

    suspend fun deleteLocation(locations: Locations)

    fun getAllLocations() : LiveData<List<Locations>>
}