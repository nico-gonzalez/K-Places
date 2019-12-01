package com.edreams.android.workshops.kotlin.data.venues.repository.venues

import android.content.res.Resources
import com.edreams.android.workshops.kotlin.data.venues.cache.dao.VenuesDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity
import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesDatasource
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesList
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale
import javax.inject.Inject

class VenuesDataRepository @Inject constructor(
    private val remoteMapper: Mapper<VenueResponse, VenueEntity>,
    private val cacheMapper: Mapper<VenueEntity, VenueModel>,
    private val exploreVenuesDatasource: ExploreVenuesDatasource,
    private val venuesDao: VenuesDao
) : VenuesRepository {

    override fun getVenues(query: String): VenuesList = flow {
        val normalizedQuery = query.toLowerCase(Locale.getDefault())
        val cachedVenues = venuesDao.findByQuery(normalizedQuery)
        if (cachedVenues.isNotEmpty()) {
            emit(cacheMapper.map(cachedVenues))
        }
        val remoteVenues = exploreVenuesDatasource.exploreVenues(normalizedQuery)
        val completeRemoteVenues = remoteMapper.map(remoteVenues)
            .map { it.copy(query = normalizedQuery) }
            .toTypedArray()

        if (completeRemoteVenues.isNotEmpty()) {
            venuesDao.clearAndInsert(normalizedQuery, completeRemoteVenues)
        }
        emit(cacheMapper.map(venuesDao.findByQuery(normalizedQuery)))
    }

    override fun getVenueDetails(id: String): Flow<VenueModel> = flow {
        exploreVenuesDatasource.getVenueDetails(id)
            ?.let(remoteMapper::map)
            ?.also { venuesDao.update(it) }
            ?.also { emit(cacheMapper.map(it)) } ?: throw Throwable("Venue $id not found")
    }
}
