package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.mapper.VenueResponseMapper
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.response.BaseFourSquareResponse
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController.ExploreVenueControllerListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreVenuesNetController(private val restService: RestService) : ExploreVenuesController {

  override fun exploreVenues(near: String, listener: ExploreVenueControllerListener) {
    restService.service.exploreVenues(near, 50, venuePhotos = 1)
        .enqueue(object : Callback<BaseFourSquareResponse> {
          override fun onResponse(call: Call<BaseFourSquareResponse>,
              response: Response<BaseFourSquareResponse>) {

            response.body()?.let {
              val fsResponse = it.response
              val venues = fsResponse.groups[0].items.mapIndexed { _, item ->
                VenueResponseMapper().map(item.venue)
              }

              listener.onGetVenuesSuccessful(venues)
            } ?: run {
              listener.onGetVenuesError(Throwable())
            }
          }

          override fun onFailure(call: Call<BaseFourSquareResponse>, t: Throwable) {
            listener.onGetVenuesError(t)
          }
        })
  }
}