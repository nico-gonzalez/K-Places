package com.edreams.android.workshops.kotlin.domain.repositories

import com.edreams.android.workshops.kotlin.domain.model.VenueModel

interface VenuesRepository {

  fun getVenues(near: String, response: (List<VenueModel>) -> Unit, error: (Throwable) -> Unit)

}