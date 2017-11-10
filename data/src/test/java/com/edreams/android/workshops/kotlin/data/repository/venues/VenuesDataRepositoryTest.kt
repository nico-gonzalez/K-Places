package com.edreams.android.workshops.kotlin.data.repository.venues

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.common.Callback
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class VenuesDataRepositoryTest {

  private val mapper: Mapper<VenueResponse, VenueModel> = mock()
  private val exploreVenuesController: ExploreVenuesController = mock()
  private val response: Callback<List<VenueModel>> = mock()
  private val error: Callback<Throwable> = mock()

  private val successCaptor: KArgumentCaptor<Callback<List<VenueResponse>>> = argumentCaptor()
  private val errorCaptor: KArgumentCaptor<Callback<Throwable>> = argumentCaptor()

  private lateinit var repository: VenuesRepository

  private val venues = listOf<VenueResponse>()

  @Before
  fun setup() {
    repository = VenuesDataRepository(mapper, exploreVenuesController)
  }

  @Test
  fun whenGetVenuesSuccessCallController() {
    val near = "Barcelona"
    repository.getVenues(near, response, error)

    verify(exploreVenuesController).exploreVenues(eq(near), successCaptor.capture(),
        errorCaptor.capture())
    successCaptor.lastValue(venues)
    inOrder(mapper, response) {
      verify(mapper).map(eq(venues))
      verify(response).invoke(eq(mapper.map(venues)))
    }
  }

  @Test
  fun whenGetVenuesFailsCallController() {
    val near = "Barcelona"
    repository.getVenues(near, response, error)

    verify(exploreVenuesController).exploreVenues(eq(near), successCaptor.capture(),
        errorCaptor.capture())
    errorCaptor.lastValue(Throwable("error"))
    verify(error).invoke(argThat { localizedMessage == "error" })
  }

}