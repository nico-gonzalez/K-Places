package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

class GetVenuesInteractor(private val controller: ExploreVenuesController) {

  fun getVenues(near: String, success: (List<VenueModel>) -> Unit, error: (Throwable) -> Unit) {
    controller.exploreVenues(near,
      success
    ,
      error
    )
  }
}