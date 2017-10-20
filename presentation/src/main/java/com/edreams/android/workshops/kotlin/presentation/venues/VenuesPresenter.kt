package com.edreams.android.workshops.kotlin.presentation.venues

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

data class VenueUiModel(val title: String, val photoUrl: String, val rating: Float,
    val formattedAddress: String?, val formattedPhone: String?)

class VenuesPresenter(private val venuesView: VenuesView,
    private val getVenuesInteractor: GetVenuesInteractor,
    private val mapper: Mapper<VenueModel, VenueUiModel>) {

  fun loadVenues(near: String) {
    venuesView.showLoading()
    getVenuesInteractor.getVenues(near, { onGetVenuesResult(it) },
        { venuesView.showError(it.localizedMessage) })
  }

  fun onSearch(queryString: String) {
    if (queryString.isEmpty()) {
      return venuesView.showEmptySearchError()
    }

    venuesView.showLoading()
    getVenuesInteractor.getVenues(queryString, { onGetVenuesResult(it) },
        { venuesView.showError(it.localizedMessage) })
  }

  private fun onGetVenuesResult(venues: List<VenueModel>) = with(venuesView) {
    hideLoading()
    showVenues(mapper.map(venues))
  }

  fun onVenueSelected(venue: VenueUiModel) {
    venuesView.showVenueDetails(venue)
  }
}
