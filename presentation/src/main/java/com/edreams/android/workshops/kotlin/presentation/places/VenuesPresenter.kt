package com.edreams.android.workshops.kotlin.presentation.places

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor.GetVenuesInteractorListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

data class VenueViewModel(val title: String)

class VenuesPresenter(private val venuesView: VenuesView,
    private val getVenuesInteractor: GetVenuesInteractor) {

  fun loadVenues() {
    venuesView.showLoading()
    getVenuesInteractor.getVenues("Barcelona", object : GetVenuesInteractorListener {
      override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
        venuesView.apply {
          hideLoading()
          showVenues(venues.mapIndexed { _, venueModel -> VenueViewModel(venueModel.name) })
        }
      }

      override fun onGetVenuesError(error: Throwable) {
        venuesView.showError(error.localizedMessage)
      }
    })
  }

  fun onSearch(queryString: String) {
    getVenuesInteractor.getVenues(queryString, object : GetVenuesInteractorListener {
      override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
        venuesView.apply {
          hideLoading()
          showVenues(venues.mapIndexed { _, venueModel -> VenueViewModel(venueModel.name) })
        }
      }

      override fun onGetVenuesError(error: Throwable) {
        venuesView.showError(error.localizedMessage)
      }
    })
  }

}