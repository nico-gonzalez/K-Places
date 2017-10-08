package com.edreams.android.workshops.kotlin.data.response

class VenueResponse(val id: String, val name: String, val rating: Float,
    val contact: Contact,
    val location: Location,
    val stats: Stats,
    val tips: List<Tip>?,
    val photos: Response<PhotoResponse>)

class Contact(val formattedPhone: String)

class Location(val formattedAddress: List<String>, val distance: Int)

class Stats(val checkinsCount: Int)

class Tip(val text: String)