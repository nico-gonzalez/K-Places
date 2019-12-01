package com.edreams.android.workshops.kotlin.data.venues.remote

import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse

interface ExploreVenuesDatasource {

  suspend fun exploreVenues(near: String): List<VenueResponse>

  suspend fun getVenueDetails(id: String): VenueResponse?
}