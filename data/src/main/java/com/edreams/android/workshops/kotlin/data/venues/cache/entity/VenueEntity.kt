package com.edreams.android.workshops.kotlin.data.venues.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "venue")
data class VenueEntity(
    @PrimaryKey val id: String,
    val name: String,
    val rating: Float,
    val photo: String?,
    val formattedPhone: String?,
    val distance: Int?,
    val formattedAddress: String?,
    val checkinsCount: Int?,
    val tips: String?,
    @ColumnInfo(index = true) val query: String? = null
)
