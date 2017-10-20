package com.edreams.android.workshops.kotlin.domain.venues

import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.util.capture
import com.edreams.android.workshops.kotlin.domain.util.eq
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetVenuesInteractorTest {

  private val ID = "1"
  private val NAME = "Sagrada familia"
  private val RATING = 3.5f
  private val PHOTO = "https://cdn.workshop.kotlin.com/image.png"
  private val DISTANCE = 500
  private val CHECKINS = 200
  private val TIPS = "A tip"
  private val PHONE = "+(34) 689 16 77 55"
  private val ADDRESS = listOf("Carrer de Bailen 67-69", "Barcelona", "Spain")

  @Mock lateinit var exploreVenuesController: ExploreVenuesController
  @Mock lateinit var success: (List<VenueModel>) -> Unit
  @Mock lateinit var error: (Throwable) -> Unit

  @Captor private lateinit var successCaptor: ArgumentCaptor<(List<VenueModel>) -> Unit>
  @Captor private lateinit var errorCaptor: ArgumentCaptor<(Throwable) -> Unit>

  private lateinit var interactor: GetVenuesInteractor

  @Before
  fun setup() {
    interactor = GetVenuesInteractor(exploreVenuesController)
  }

  @Test
  fun `When getVenues success calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, success, error)

    verify(exploreVenuesController).exploreVenues(eq(near), capture(successCaptor),
        capture(errorCaptor))

    val venues = buildMockVenues()
    successCaptor.value.invoke(venues)

    verify(success).invoke(venues)
  }

  @Test
  fun `When getVenues error calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, success, error)

    verify(exploreVenuesController).exploreVenues(
        eq(near),
        capture(successCaptor), capture(errorCaptor))

    val error = Throwable("Error")
    errorCaptor.value.invoke(error)

    verify(this.error).invoke(error)
  }

  private fun buildMockVenues(): List<VenueModel> = listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS)
  )

}