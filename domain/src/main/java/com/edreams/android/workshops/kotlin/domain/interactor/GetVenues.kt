package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.GetVenuesResult
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesList
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVenues @Inject constructor(
    private val repository: VenuesRepository,
    executor: Executor
) : UseCase(executor) {

    fun execute(near: String): Flow<GetVenuesResult> =
        repository.getVenues(near).map { venues ->
            if (venues.isEmpty()) {
                GetVenuesResult(error = Throwable("No venues where found"))
            } else {
                GetVenuesResult(venues)
            }
        }.catch { emit(GetVenuesResult(error = it)) }
            .flowOn(Dispatchers.IO)
}
