package com.edreams.android.workshops.kotlin.data.venues.repository.venues

import com.edreams.android.workshops.kotlin.data.venues.cache.dao.VenuesDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity
import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesListProducer
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import javax.inject.Inject

class VenuesDataRepository @Inject constructor(
    private val remoteMapper: Mapper<VenueResponse, VenueEntity>,
    private val cacheMapper: Mapper<VenueEntity, VenueModel>,
    private val exploreVenuesController: ExploreVenuesController,
    private val venuesDao: VenuesDao) : VenuesRepository {

  override suspend fun getVenues(query: String): VenuesListProducer = produce {
    val normalizedQuery = query.toLowerCase()
    val cachedVenues = venuesDao.findByQuery(normalizedQuery)
    if (cachedVenues.isNotEmpty()) {
      send(cacheMapper.map(cachedVenues))
    }
    val remoteVenues = remoteMapper.map(
        exploreVenuesController.exploreVenues(normalizedQuery))
        .map {
          it.copy(query = normalizedQuery)
        }
        .toTypedArray()

    if (remoteVenues.isNotEmpty()) {
      venuesDao.clearAndInsert(normalizedQuery, remoteVenues)
    }
    send(cacheMapper.map(venuesDao.findByQuery(normalizedQuery)))
  }
}
