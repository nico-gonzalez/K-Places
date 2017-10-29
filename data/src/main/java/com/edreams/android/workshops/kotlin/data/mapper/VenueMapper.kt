package com.edreams.android.workshops.kotlin.data.mapper

import com.edreams.android.workshops.kotlin.data.response.PhotoResponse
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import javax.inject.Inject

class VenueMapper @Inject constructor() : Mapper<VenueResponse, VenueModel> {

  override fun map(from: VenueResponse): VenueModel = with(from) {
    return VenueModel(id,
        name,
        rating,
        buildPhotoUrl(photos.groups[0].items[0]),
        contact.formattedPhone,
        location.distance,
        location.formattedAddress,
        stats.checkinsCount,
        tips?.get(0)?.text
    )

  }

  private fun buildPhotoUrl(photo: PhotoResponse): String = with(photo) {
    "$prefix${width}x$height$suffix"
  }
}