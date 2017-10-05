package com.edreams.android.workshops.kotlin.presentation.presenter

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor.GetVenuesInteractorListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

class GetVenuesPresenter(private val interactor: GetVenuesInteractor) {

  fun getVenues() {

    interactor.getVenues(object : GetVenuesInteractorListener {
      override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }

      override fun onGetVenuesError() {
        TODO(
            "not implemented") //To change body of created functions use File | Settings | File Templates.
      }
    })
  }
}