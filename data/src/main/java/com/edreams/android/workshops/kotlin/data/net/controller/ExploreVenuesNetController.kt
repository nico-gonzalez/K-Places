package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.net.service.FoursquareService
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExploreVenuesNetController @Inject constructor(
    private val service: FoursquareService) : ExploreVenuesController {

  suspend override fun exploreVenues(near: String): List<VenueResponse> {
    val response = service.exploreVenues(near, 50, venuePhotos = 1).execute()
    return response.body()?.let {
      return it.response.groups[0].items.map { it.venue }
    } ?: run {
      emptyList<VenueResponse>()
    }
  }
}