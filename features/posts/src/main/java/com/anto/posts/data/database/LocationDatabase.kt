package com.anto.posts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationsEntity::class], version = 1)
abstract class LocationDatabase : RoomDatabase() {
    abstract val dao: LocationsDao
}