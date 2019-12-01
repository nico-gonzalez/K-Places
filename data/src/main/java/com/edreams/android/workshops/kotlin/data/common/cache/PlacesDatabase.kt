package com.edreams.android.workshops.kotlin.data.common.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.edreams.android.workshops.kotlin.data.venues.cache.dao.VenuesDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity

@Database(entities = [
  VenueEntity::class
], version = 1)
abstract class PlacesDatabase : RoomDatabase() {

  abstract fun venuesDao(): VenuesDao
}