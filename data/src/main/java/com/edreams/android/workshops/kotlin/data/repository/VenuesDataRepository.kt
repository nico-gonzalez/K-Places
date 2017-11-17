package com.edreams.android.workshops.kotlin.data.repository

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import javax.inject.Inject

class VenuesDataRepository @Inject constructor(
    private val mapper: Mapper<VenueResponse, VenueModel>,
    private val exploreVenuesController: ExploreVenuesController) : VenuesRepository {

  suspend override fun getVenues(near: String): List<VenueModel> =
      mapper.map(exploreVenuesController.exploreVenues(near))
}