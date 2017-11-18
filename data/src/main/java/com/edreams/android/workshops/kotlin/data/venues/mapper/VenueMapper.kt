package com.edreams.android.workshops.kotlin.data.venues.mapper

import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity
import com.edreams.android.workshops.kotlin.data.venues.remote.response.PhotoResponse
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import javax.inject.Inject

class VenueMapper @Inject constructor() : Mapper<VenueResponse, VenueEntity> {

  override fun map(from: VenueResponse): VenueEntity = with(from) {
    return VenueEntity(id,
        name,
        rating,
        buildPhotoUrl(photos.groups[0].items[0]),
        contact.formattedPhone,
        location.distance,
        location.formattedAddress.joinToString(","),
        stats.checkinsCount,
        tips?.get(0)?.text
    )

  }

  private fun buildPhotoUrl(photo: PhotoResponse): String = with(photo) {
    "$prefix${width}x$height$suffix"
  }
}