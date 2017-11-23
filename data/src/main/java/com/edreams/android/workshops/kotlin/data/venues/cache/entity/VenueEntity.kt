package com.edreams.android.workshops.kotlin.data.venues.cache.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "venue")
data class VenueEntity(@PrimaryKey val id: String, val name: String, val rating: Float,
    val photo: String, val formattedPhone: String?, val distance: Int,
    val formattedAddress: String?, val checkinsCount: Int, val tips: String?,
    val query: String? = null)