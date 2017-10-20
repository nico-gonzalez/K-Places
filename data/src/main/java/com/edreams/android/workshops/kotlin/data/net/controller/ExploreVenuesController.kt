package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.response.VenueResponse

interface ExploreVenuesController {

  fun exploreVenues(near: String, success: (List<VenueResponse>) -> Unit,
      error: (Throwable) -> Unit)
}