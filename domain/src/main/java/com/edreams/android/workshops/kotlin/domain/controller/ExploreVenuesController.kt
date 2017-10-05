package com.edreams.android.workshops.kotlin.domain.controller

import com.edreams.android.workshops.kotlin.domain.model.VenueModel

interface ExploreVenuesController {

  fun exploreVenues(near: String, listener: ExploreVenueControllerListener)

  interface ExploreVenueControllerListener {

    fun onGetVenuesSuccessful(venues : List<VenueModel>)
    fun onGetVenuesError()
  }
}