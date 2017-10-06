package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.mapper.VenueResponseMapper
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.response.BaseFourSquareResponse
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController.ExploreVenueControllerListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreVenuesNetController : ExploreVenuesController {

  private val restService: RestService = RestService()

  override fun exploreVenues(near: String, listener: ExploreVenueControllerListener) {
    val call = restService.service.exploreVenues(
        restService.API_VERSION,
        restService.CLIENT_ID,
        restService.CLIENT_SECRET,
        restService.BUNDLE_VERSION,
        near, 50, 1)

    call.enqueue(object : Callback<BaseFourSquareResponse> {
      override fun onResponse(call: Call<BaseFourSquareResponse>,
          response: Response<BaseFourSquareResponse>) {

        response.body()?.let {
          val fsResponse = it.response
          val venues = fsResponse.groups[0].items.mapIndexed { _, item ->
            VenueResponseMapper().map(item.venue)
          }

          listener.onGetVenuesSuccessful(venues)
        } ?: run {
          listener.onGetVenuesError()
        }
      }

      override fun onFailure(call: Call<BaseFourSquareResponse>, t: Throwable) {
        listener.onGetVenuesError()
      }
    })
  }
}