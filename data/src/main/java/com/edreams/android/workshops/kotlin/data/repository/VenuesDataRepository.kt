package com.edreams.android.workshops.kotlin.data.repository

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository

class VenuesDataRepository(
    private val mapper: Mapper<VenueResponse, VenueModel>,
    private val exploreVenuesController: ExploreVenuesController) : VenuesRepository {

  override fun getVenues(near: String, response: (List<VenueModel>) -> Unit,
      error: (Throwable) -> Unit) = with(exploreVenuesController) {
    exploreVenues(near, { venues -> response(mapper.map(venues)) }, error)
  }
}