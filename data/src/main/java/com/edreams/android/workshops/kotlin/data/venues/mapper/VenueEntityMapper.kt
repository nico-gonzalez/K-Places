package com.edreams.android.workshops.kotlin.data.venues.mapper

import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import javax.inject.Inject

class VenueEntityMapper @Inject constructor() : Mapper<VenueEntity, VenueModel> {

  override fun map(from: VenueEntity): VenueModel = with(from) {
    return VenueModel(id,
        name,
        rating,
        photo,
        formattedPhone,
        distance,
        formattedAddress?.split(","),
        checkinsCount,
        tips,
        query
    )
  }
}