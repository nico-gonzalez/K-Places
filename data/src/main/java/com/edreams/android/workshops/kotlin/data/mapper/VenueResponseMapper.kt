package com.edreams.android.workshops.kotlin.data.mapper

import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

class VenueResponseMapper {
  fun map(venueResponse: VenueResponse): VenueModel {
    return VenueModel(venueResponse.id,
        venueResponse.name,
        venueResponse.rating,
        venueResponse.photos.groups[0].items[0].prefix +
            venueResponse.photos.groups[0].items[0].width + "x" +
            venueResponse.photos.groups[0].items[0].height +
            venueResponse.photos.groups[0].items[0].suffix)

  }
}