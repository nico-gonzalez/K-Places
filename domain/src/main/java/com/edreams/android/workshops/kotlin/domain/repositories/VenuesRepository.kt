package com.edreams.android.workshops.kotlin.domain.repositories

import com.edreams.android.workshops.kotlin.domain.common.Callback
import com.edreams.android.workshops.kotlin.domain.model.VenueModel

interface VenuesRepository {

  fun getVenues(near: String, response: Callback<List<VenueModel>>, error: Callback<Throwable>)

}