package com.edreams.android.workshops.kotlin.data.venues.remote.response

data class VenueResponse(val id: String, val name: String, val rating: Float,
    val contact: Contact,
    val location: Location,
    val stats: Stats,
    val tips: List<Tip>?,
    val photos: Response<PhotoResponse>)

data class Contact(val formattedPhone: String)

data class Location(val formattedAddress: List<String>, val distance: Int)

data class Stats(val checkinsCount: Int)

data class Tip(val text: String)