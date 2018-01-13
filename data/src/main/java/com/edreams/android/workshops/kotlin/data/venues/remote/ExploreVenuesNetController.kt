package com.edreams.android.workshops.kotlin.data.venues.remote

import com.edreams.android.workshops.kotlin.data.common.remote.FoursquareService
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val RESULTS_LIMIT = 50
private const val PHOTOS_COUNT = 1

@Singleton
class ExploreVenuesNetController @Inject constructor(
    private val service: FoursquareService) : ExploreVenuesController {

  suspend override fun exploreVenues(near: String): List<VenueResponse> = try {
    service.exploreVenues(near, RESULTS_LIMIT, venuePhotos = PHOTOS_COUNT)
        .await().response.groups[0].items.map { it.venue }
  } catch (http: HttpException) {
    emptyList()
  } catch (network: IOException) {
    emptyList()
  }
}

