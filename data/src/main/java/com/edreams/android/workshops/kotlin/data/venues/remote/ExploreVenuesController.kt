package com.edreams.android.workshops.kotlin.data.venues.remote

import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse

interface ExploreVenuesController {

  suspend fun exploreVenues(near: String): List<VenueResponse>
}