package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.pojo.BaseFourSquareResponse
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController.ExploreVenueControllerListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
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
        near)

    call.enqueue(object : Callback<BaseFourSquareResponse> {
      override fun onResponse(call: Call<BaseFourSquareResponse>,
          response: Response<BaseFourSquareResponse>) {

        response.body()?.let {
          val fsResponse = it.response

          val venues = fsResponse.groups[0].items.mapIndexed { _, item ->
            VenueModel(item.venue.id, item.venue.name)
          }

          listener.onGetVenuesSuccessful(venues)
        }?: run {
          listener.onGetVenuesError()
        }
      }

      override fun onFailure(call: Call<BaseFourSquareResponse>, t: Throwable) {
        listener.onGetVenuesError()
      }
    })
  }
}