package com.edreams.android.workshops.kotlin.data.common.remote

import com.edreams.android.workshops.kotlin.data.venues.remote.response.FourSquareResponse
import com.edreams.android.workshops.kotlin.data.venues.remote.response.Response
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenuesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoursquareService {

  @GET("/v2/venues/explore")
  suspend fun exploreVenues(
    @Query("near") near: String,
    @Query("limit") limit: Int,
    @Query("venuePhotos") venuePhotos: Int
  ): FourSquareResponse<Response<VenuesResponse>>

  @GET("/v2/venues/{id}")
  suspend fun getVenueDetails(@Path("id") id: String): FourSquareResponse<VenuesResponse>
}
