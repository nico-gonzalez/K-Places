package com.edreams.android.workshops.kotlin.data.net.service

import com.edreams.android.workshops.kotlin.data.response.BaseFourSquareResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

  @GET("/{version}/venues/explore")
  fun exploreVenues(
      @Path("version") version: String,
      @Query("client_id") id: String,
      @Query("client_secret") secret: String,
      @Query("v") v: String,
      @Query("near") near: String,
      @Query("limit") limit: Int,
      @Query("venuePhotos") venuePhotos: Int
  ): Call<BaseFourSquareResponse>
}
