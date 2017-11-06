package com.edreams.android.workshops.kotlin.presentation.venues.model

data class VenueUiModel(val title: String, val photoUrl: String, val rating: Float,
    val formattedAddress: String?, val formattedPhone: String?)

class VenuesUiModel private constructor(val venues: List<VenueUiModel> = emptyList(),
    val progress: Boolean = false,
    val error: String = "") {

  companion object {

    fun progress() = VenuesUiModel(progress = true)

    fun success(venues: List<VenueUiModel>) = VenuesUiModel(venues = venues)

    fun error(errorMessage: String) = VenuesUiModel(error = errorMessage)

  }
}