package com.anto.weatherapp.data.local.mappers

import com.anto.weatherapp.data.local.LocationsEntity
import com.anto.posts.domain.entity.Locations

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