package com.edreams.android.workshops.kotlin.domain.controller

import com.edreams.android.workshops.kotlin.domain.model.VenueModel

interface ExploreVenuesController {

  fun exploreVenues(near: String, success: (List<VenueModel>) -> Unit, error: (Throwable) -> Unit)
}