package com.edreams.android.workshops.kotlin.data.repository

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.common.Callback
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import javax.inject.Inject

class VenuesDataRepository @Inject constructor(
    private val mapper: Mapper<VenueResponse, VenueModel>,
    private val exploreVenuesController: ExploreVenuesController) : VenuesRepository {

  override fun getVenues(near: String, response: Callback<List<VenueModel>>,
      error: Callback<Throwable>) = with(exploreVenuesController) {
    exploreVenues(near, { venues -> response(mapper.map(venues)) }, error)
  }
}