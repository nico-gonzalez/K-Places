package com.edreams.android.workshops.kotlin.data.repository.venues

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VenuesDataRepositoryTest {

  @Mock private lateinit var mapper: Mapper<VenueResponse, VenueModel>
  @Mock private lateinit var exploreVenuesController: ExploreVenuesController
  @Mock private lateinit var response: (List<VenueModel>) -> Unit
  @Mock private lateinit var error: (Throwable) -> Unit

  @Captor private lateinit var successCaptor: ArgumentCaptor<(List<VenueResponse>) -> Unit>
  @Captor private lateinit var errorCaptor: ArgumentCaptor<(Throwable) -> Unit>

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

    verify(exploreVenuesController).exploreVenues(eq(near), capture(successCaptor),
        capture(errorCaptor))
    successCaptor.value.invoke(venues)
    inOrder(mapper, response) {
      verify(mapper).map(eq(venues))
      verify(response).invoke(eq(mapper.map(venues)))
    }
  }

  @Test
  fun whenGetVenuesFailsCallController() {
    val near = "Barcelona"
    repository.getVenues(near, response, error)

    verify(exploreVenuesController).exploreVenues(eq(near), capture(successCaptor),
        capture(errorCaptor))
    errorCaptor.value.invoke(Throwable("error"))
    verify(error).invoke(argThat { localizedMessage == "error" })
  }

}