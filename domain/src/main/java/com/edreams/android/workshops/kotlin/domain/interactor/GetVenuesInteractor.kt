package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

class GetVenuesInteractor(private val controller: ExploreVenuesController) {

  fun getVenues(near: String, listener: GetVenuesInteractorListener) {
    controller.exploreVenues(near,
        object : ExploreVenuesController.ExploreVenueControllerListener {
          override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
            listener.onGetVenuesSuccessful(venues)
          }

          override fun onGetVenuesError(error: Throwable) {
            listener.onGetVenuesError(error)
          }
        })
  }

  interface GetVenuesInteractorListener {
    fun onGetVenuesSuccessful(venues: List<VenueModel>)

    fun onGetVenuesError(error: Throwable)
  }
}