package com.edreams.android.workshops.kotlin.presentation.venues.model

data class VenueUiModel(
    val id: String,
    val title: String,
    val photoUrl: String,
    val rating: Float,
    val formattedAddress: String?,
    val formattedPhone: String?
)

class VenuesUiModel private constructor(
    val venues: List<VenueUiModel> = emptyList(),
    val progress: Boolean = false,
    val error: String = ""
) {

    companion object {
        fun progress() = VenuesUiModel(progress = true)
        fun success(venues: List<VenueUiModel>) = VenuesUiModel(venues = venues)
        fun error(errorMessage: String) = VenuesUiModel(error = errorMessage)
    }
}

class VenueDetailsUiModel private constructor(
    val venue: VenueUiModel? = null,
    val progress: Boolean = false,
    val error: String = ""
) {

    companion object {
        fun progress() = VenueDetailsUiModel(progress = true)
        fun success(venue: VenueUiModel) = VenueDetailsUiModel(venue = venue)
        fun error(errorMessage: String) = VenueDetailsUiModel(error = errorMessage)
    }
}
