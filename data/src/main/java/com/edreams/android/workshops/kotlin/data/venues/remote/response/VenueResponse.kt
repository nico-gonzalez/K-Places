package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class VenueResponse(val id: String, val name: String, @Transient val rating: Float = 0f,
    val contact: Contact,
    val location: Location,
    val stats: Stats,
    @Transient val tips: List<Tip> = emptyList(),
    val photos: Response<PhotoResponse>)

@Serializable
data class Contact(@Transient val formattedPhone: String = "")

@Serializable
data class Location(val formattedAddress: List<String>, @Transient val distance: Int = -1)

@Serializable
data class Stats(val checkinsCount: Int)

@Serializable
data class Tip(val text: String)