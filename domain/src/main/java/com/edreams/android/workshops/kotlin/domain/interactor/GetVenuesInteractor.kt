package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import javax.inject.Inject

class GetVenuesInteractor @Inject constructor(private val repository: VenuesRepository) {

  fun getVenues(near: String, success: (List<VenueModel>) -> Unit,
      error: (Throwable) -> Unit) = with(repository) {
    getVenues(near, success, error)
  }
}