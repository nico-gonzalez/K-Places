package com.edreams.android.workshops.kotlin.domain.interactor

import com.edreams.android.workshops.kotlin.domain.common.GetVenueDetailsResult
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetVenueDetails @Inject constructor(
    private val repository: VenuesRepository,
    executor: Executor
) : UseCase(executor) {

    fun execute(id: String): Flow<GetVenueDetailsResult> =
        repository.getVenueDetails(id)
            .map { venue -> GetVenueDetailsResult(venue) }
            .catch { emit(GetVenueDetailsResult(error = it)) }
            .flowOn(Dispatchers.IO)
}
