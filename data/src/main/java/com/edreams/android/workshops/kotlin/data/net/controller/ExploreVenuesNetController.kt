package com.edreams.android.workshops.kotlin.data.net.controller

import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.response.FourSquareResponse
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.data.response.VenuesResponse
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController.ExploreVenueControllerListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreVenuesNetController(private val restService: RestService,
    private val mapper: Mapper<VenueResponse, VenueModel>) : ExploreVenuesController {

  override fun exploreVenues(near: String, listener: ExploreVenueControllerListener) {
    restService.service.exploreVenues(near, 50, venuePhotos = 1)
        .enqueue(object : Callback<FourSquareResponse<VenuesResponse>> {
          override fun onResponse(call: Call<FourSquareResponse<VenuesResponse>>,
              response: Response<FourSquareResponse<VenuesResponse>>) {

            response.body()?.let {
              val venues = mapper.map(it.response.groups[0].items.map { it.venue })
              listener.onGetVenuesSuccessful(venues)
            } ?: run {
              listener.onGetVenuesError(Throwable())
            }
          }

          override fun onFailure(call: Call<FourSquareResponse<VenuesResponse>>, t: Throwable) {
            listener.onGetVenuesError(t)
          }
        })
  }
}