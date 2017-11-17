package com.edreams.android.workshops.kotlin.data.repository.venues

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
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
  private val venues = listOf<VenueModel>()

  private val mapper: Mapper<VenueResponse, VenueModel> = mock {
    on { map(remoteVenues) }.doReturn(venues)
  }
  private val exploreVenuesController: ExploreVenuesController = mock()

  private lateinit var repository: VenuesRepository

  @Before
  fun setup() {
    repository = VenuesDataRepository(mapper, exploreVenuesController)
  }

  @Test
  fun whenGetVenuesSuccessCallController() {
    val near = "Barcelona"
    runBlocking {
      whenever(exploreVenuesController.exploreVenues(eq(near)))
          .thenReturn(remoteVenues)
      val result = repository.getVenues(near)
      verify(exploreVenuesController).exploreVenues(eq(near))
      assertThat(result, `is`(equalTo(venues)))
    }
  }

  @Test
  fun whenGetVenuesFailsCallController() {
    val near = "Barcelona"
    runBlocking {
      whenever(exploreVenuesController.exploreVenues(eq(near)))
          .thenReturn(emptyList())
      val result = repository.getVenues(near)
      verify(exploreVenuesController).exploreVenues(eq(near))
      assertThat(result.size, `is`(equalTo(0)))
    }
  }
}