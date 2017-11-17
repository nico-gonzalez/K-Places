package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.response.VenueResponse

interface ExploreVenuesController {

  suspend fun exploreVenues(near: String): List<VenueResponse>
}