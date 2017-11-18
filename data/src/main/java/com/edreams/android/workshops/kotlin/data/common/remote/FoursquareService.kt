package com.edreams.android.workshops.kotlin.data.common.remote

import com.edreams.android.workshops.kotlin.data.BuildConfig.FS_CLIENT_ID
import com.edreams.android.workshops.kotlin.data.BuildConfig.FS_CLIENT_SECRET
import com.edreams.android.workshops.kotlin.data.venues.remote.response.FourSquareResponse
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoursquareService {

  @GET("/v2/venues/explore?" +
      "v=20170801" +
      "&client_id=$FS_CLIENT_ID" +
      "&client_secret=$FS_CLIENT_SECRET")
  fun exploreVenues(
      @Query("near") near: String,
      @Query("limit") limit: Int,
      @Query("venuePhotos") venuePhotos: Int
  ): Call<FourSquareResponse<VenuesResponse>>
}
