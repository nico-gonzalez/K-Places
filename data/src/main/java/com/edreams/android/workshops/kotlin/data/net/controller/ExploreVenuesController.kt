package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.common.Callback

interface ExploreVenuesController {

  fun exploreVenues(near: String, success: Callback<List<VenueResponse>>,
      error: Callback<Throwable>)
}