package com.edreams.android.workshops.kotlin.data.venues.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class VenuesResponse(val venue: VenueResponse)