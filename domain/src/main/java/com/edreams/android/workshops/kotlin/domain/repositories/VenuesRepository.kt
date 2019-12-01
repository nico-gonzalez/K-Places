package com.edreams.android.workshops.kotlin.domain.repositories

import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import kotlinx.coroutines.flow.Flow

typealias VenuesList = Flow<List<VenueModel>>

interface VenuesRepository {

    fun getVenues(query: String): VenuesList

    fun getVenueDetails(id: String): Flow<VenueModel>
}
