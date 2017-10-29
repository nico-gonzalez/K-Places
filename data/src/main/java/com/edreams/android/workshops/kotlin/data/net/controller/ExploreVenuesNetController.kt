package com.edreams.android.workshops.kotlin.data.net.controller

import android.support.test.espresso.idling.CountingIdlingResource
import com.edreams.android.workshops.kotlin.data.net.service.FoursquareService
import com.edreams.android.workshops.kotlin.data.response.FourSquareResponse
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.data.response.VenuesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ExploreVenuesNetController @Inject constructor(private val service: FoursquareService,
    private val idlingResource: CountingIdlingResource) : ExploreVenuesController {

  override fun exploreVenues(near: String, success: (List<VenueResponse>) -> Unit,
      error: (Throwable) -> Unit) {
    idlingResource.increment()
    service.exploreVenues(near, 50, venuePhotos = 1).enqueue(
        object : Callback<FourSquareResponse<VenuesResponse>> {
          override fun onResponse(call: Call<FourSquareResponse<VenuesResponse>>,
              response: Response<FourSquareResponse<VenuesResponse>>) {
            idlingResource.decrement()
            response.body()?.let {
              val venues = it.response.groups[0].items.map { it.venue }
              success(venues)
            } ?: run {
              error(Throwable("Unexpected error"))
            }
          }

          override fun onFailure(call: Call<FourSquareResponse<VenuesResponse>>, t: Throwable) {
            idlingResource.decrement()
            error(t)
          }
        })
  }
}