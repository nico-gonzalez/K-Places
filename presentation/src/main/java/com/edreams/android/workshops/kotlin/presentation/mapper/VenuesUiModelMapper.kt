package com.edreams.android.workshops.kotlin.presentation.mapper

import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import javax.inject.Inject

class VenuesUiModelMapper @Inject constructor() : Mapper<VenueModel, VenueUiModel> {

  override fun map(from: VenueModel): VenueUiModel = with(from) {
    VenueUiModel(name,
        photo,
        normalizeRating(rating),
        formattedAddress?.reduce { result, item -> result + ", " + item },
        formattedPhone
    )
  }

  private fun normalizeRating(rating: Float) = rating * 5f / 10f
}