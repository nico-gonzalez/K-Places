package com.edreams.android.workshops.kotlin.domain.repositories

import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import kotlinx.coroutines.experimental.channels.ReceiveChannel

typealias VenuesListProducer = ReceiveChannel<List<VenueModel>>

interface VenuesRepository : Repository {

  suspend fun getVenues(query: String): VenuesListProducer

}
