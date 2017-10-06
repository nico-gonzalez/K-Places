package com.edreams.android.workshops.kotlin.presentation.places

interface VenuesView {

  fun showVenues(venues: List<VenueViewModel>)

  fun showError(error: String)

  fun showLoading()

  fun hideLoading()
}