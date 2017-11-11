package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.Callback
import com.edreams.android.workshops.kotlin.domain.common.GetVenuesResult
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import javax.inject.Inject

class GetVenuesInteractor @Inject constructor(
    private val repository: VenuesRepository,
    executor: Executor) : BaseInteractor(executor) {

  fun getVenues(near: String, success: Callback<GetVenuesResult>,
      error: Callback<GetVenuesResult>) {
    postExecute {
      val getVenues = asyncExecute {
        repository.getVenues(near)
      }
      val venues = getVenues.await()
      if (venues.isEmpty()) {
        error(GetVenuesResult(venues, Throwable("No venues where found")))
      } else {
        success(GetVenuesResult(venues))
      }
    }
  }
}