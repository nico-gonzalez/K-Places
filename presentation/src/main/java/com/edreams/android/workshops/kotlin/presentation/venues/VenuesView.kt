package com.edreams.android.workshops.kotlin.presentation.venues

interface VenuesView {

  fun showVenues(venues: List<VenueUiModel>)

  fun showError(error: String)

  fun showLoading()

  fun hideLoading()

  fun showVenueDetails(venue: VenueUiModel)

  fun showEmptySearchError()
}