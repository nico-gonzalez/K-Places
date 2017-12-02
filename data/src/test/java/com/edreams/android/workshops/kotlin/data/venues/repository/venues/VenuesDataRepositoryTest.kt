package com.edreams.android.workshops.kotlin.data.venues.repository.venues

import com.edreams.android.workshops.kotlin.data.venues.cache.dao.VenuesDao
import com.edreams.android.workshops.kotlin.data.venues.cache.entity.VenueEntity
import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.venues.remote.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.experimental.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class VenuesDataRepositoryTest {

  private val remoteVenues = listOf<VenueResponse>()
  private val cachedVenues = listOf<VenueEntity>()
  private val venues = listOf<VenueModel>()

  private val remoteMapper: Mapper<VenueResponse, VenueEntity> = mock {
    on { map(remoteVenues) } doReturn cachedVenues
  }
  private val cacheMapper: Mapper<VenueEntity, VenueModel> = mock {
    on { map(cachedVenues) } doReturn venues
  }
  private val exploreVenuesController: ExploreVenuesController = mock()
  private val venuesDao: VenuesDao = mock()

  private lateinit var repository: VenuesRepository

  @Before
  fun setup() {
    repository = VenuesDataRepository(
        remoteMapper, cacheMapper, exploreVenuesController, venuesDao)
  }

  @Test
  fun whenGetVenuesSuccessCallController() = runBlocking {
    val near = "Barcelona".toLowerCase()
    whenever(exploreVenuesController.exploreVenues(eq(near))) doReturn remoteVenues

    val result = repository.getVenues(near).receive()

    verify(exploreVenuesController).exploreVenues(eq(near))
    assertThat(result, `is`(equalTo(venues)))
  }

  @Test
  fun whenGetVenuesFailsCallController() = runBlocking {
    val near = "Barcelona".toLowerCase()
    whenever(
        exploreVenuesController.exploreVenues(eq(near))) doReturn emptyList<VenueResponse>()

    val result = repository.getVenues(near).receive()

    verify(exploreVenuesController).exploreVenues(eq(near))
    assertThat(result.size, `is`(equalTo(0)))
  }
}