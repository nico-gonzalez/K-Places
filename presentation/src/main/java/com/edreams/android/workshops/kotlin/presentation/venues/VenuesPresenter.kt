package com.edreams.android.workshops.kotlin.presentation.venues

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor.GetVenuesInteractorListener
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

data class VenueUiModel(val title: String, val photoUrl: String, val rating: Float)

class VenuesPresenter(private val venuesView: VenuesView,
    private val getVenuesInteractor: GetVenuesInteractor,
    private val mapper: Mapper<VenueModel, VenueUiModel>) {

  fun loadVenues() {
    venuesView.showLoading()
    getVenuesInteractor.getVenues("Barcelona", object : GetVenuesInteractorListener {
      override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
        onGetVenuesResult(venues)
      }

      override fun onGetVenuesError(error: Throwable) {
        venuesView.showError(error.localizedMessage)
      }
    })
  }

  fun onSearch(queryString: String) {
    getVenuesInteractor.getVenues(queryString, object : GetVenuesInteractorListener {
      override fun onGetVenuesSuccessful(venues: List<VenueModel>) {
        onGetVenuesResult(venues)
      }

      override fun onGetVenuesError(error: Throwable) {
        venuesView.showError(error.localizedMessage)
      }
    })
  }

  private fun onGetVenuesResult(venues: List<VenueModel>) {
    venuesView.apply {
      hideLoading()
      showVenues(mapper.map(venues))
    }
  }

  fun onVenueSelected(venue: VenueUiModel) {
    venuesView.showVenueDetails(venue)
  }
}
