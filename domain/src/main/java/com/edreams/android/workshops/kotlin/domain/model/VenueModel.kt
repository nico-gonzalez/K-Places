package com.edreams.android.workshops.kotlin.domain.model

data class VenueModel(val id: String, val name: String, val rating: Float, val photo: String,
    val formattedPhone: String?, val distance: Int, val formattedAddress: List<String>?,
    val checkinsCount: Int, val tips: String?)