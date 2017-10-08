package com.edreams.android.workshops.kotlin.data.net.service

import com.edreams.android.workshops.kotlin.data.response.FourSquareResponse
import com.edreams.android.workshops.kotlin.data.response.VenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoursquareService {

  @GET(
      "/v2/venues/explore?" +
          "v=20170801" +
          "&client_id=GLZHBOBR0XIQOWEXIWBLC0ZDD1CFTPPGJBW310YG5BBLCRJX" +
          "&client_secret=NXGWTTRJPTCMOZ2IPEIWJYSZVU3SZKSNJCUALHLDFIEHNVDH")
  fun exploreVenues(
      @Query("near") near: String,
      @Query("limit") limit: Int,
      @Query("venuePhotos") venuePhotos: Int
  ): Call<FourSquareResponse<VenuesResponse>>
}
