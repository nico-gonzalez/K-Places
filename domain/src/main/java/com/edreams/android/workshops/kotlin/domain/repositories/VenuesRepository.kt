package com.edreams.android.workshops.kotlin.domain.repositories

import com.edreams.android.workshops.kotlin.domain.model.VenueModel

interface VenuesRepository {

  suspend fun getVenues(near: String): List<VenueModel>

}