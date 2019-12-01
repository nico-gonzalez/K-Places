package com.edreams.android.workshops.kotlin.data.venues.remote

import com.edreams.android.workshops.kotlin.data.common.remote.FoursquareService
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val RESULTS_LIMIT = 50
private const val PHOTOS_COUNT = 1

class ExploreVenuesApiDatasource @Inject constructor(
  private val service: FoursquareService
) : ExploreVenuesDatasource {

  override suspend fun exploreVenues(near: String): List<VenueResponse> = try {
    service.exploreVenues(
      near,
      RESULTS_LIMIT,
      venuePhotos = PHOTOS_COUNT
    ).response.groups.first().items.map { it.venue }
  } catch (http: HttpException) {
    emptyList()
  } catch (network: IOException) {
    emptyList()
  }

  override suspend fun getVenueDetails(id: String): VenueResponse? = try {
    service.getVenueDetails(id).response.venue
  } catch (http: HttpException) {
    null
  } catch (network: IOException) {
    null
  }
}

