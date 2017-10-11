package com.edreams.android.workshops.kotlin.presentation.venues

data class VenueUiModel(val title: String, val photoUrl: String, val rating: Float,
    val formattedAddress: String?, val formattedPhone: String?)