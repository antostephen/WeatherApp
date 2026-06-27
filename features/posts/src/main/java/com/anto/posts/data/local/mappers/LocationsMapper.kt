package com.anto.posts.data.local.mappers

import com.anto.posts.data.database.LocationsEntity
import com.anto.posts.domain.entities.Locations

fun LocationsEntity.toLocations(): Locations {
    return Locations(
        locationName = locationName,
        id = id
    )
}

fun Locations.toLocationsEntity(): LocationsEntity {
    return LocationsEntity(
        locationName = locationName,
        id = id
    )
}