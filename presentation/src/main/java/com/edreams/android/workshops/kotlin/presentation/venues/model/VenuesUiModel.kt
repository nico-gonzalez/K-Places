package com.edreams.android.workshops.kotlin.presentation.venues.model

import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel

class VenuesUiModel private constructor(val venues: List<VenueUiModel> = emptyList(),
    val progress: Boolean = false,
    val error: String = "") {

  companion object {

    fun progress() = VenuesUiModel(progress = true)

    fun success(venues: List<VenueUiModel>) = VenuesUiModel(venues = venues)

    fun error(errorMessage: String) = VenuesUiModel(error = errorMessage)

  }
}