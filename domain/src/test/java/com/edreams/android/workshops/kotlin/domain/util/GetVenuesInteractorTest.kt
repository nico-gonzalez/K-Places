package com.edreams.android.workshops.kotlin.domain.util

import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController.ExploreVenueControllerListener
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor.GetVenuesInteractorListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.venues.capture
import com.edreams.android.workshops.kotlin.domain.venues.eq
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

  @Mock lateinit var getVenuesInteractorListener: GetVenuesInteractorListener

  @Captor lateinit var exploreVenuesControllerListener: ArgumentCaptor<ExploreVenueControllerListener>

  private lateinit var interactor: GetVenuesInteractor

  @Before
  fun setup() {
    interactor = GetVenuesInteractor(exploreVenuesController)
  }

  @Test
  fun `When getVenues success calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, getVenuesInteractorListener)

    verify(exploreVenuesController).exploreVenues(eq(near),
        capture(exploreVenuesControllerListener))

    val venues = buildMockVenues()
    exploreVenuesControllerListener.value.onGetVenuesSuccessful(venues)

    verify(getVenuesInteractorListener).onGetVenuesSuccessful(venues)
  }

  @Test
  fun `When getVenues error calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, getVenuesInteractorListener)

    verify(exploreVenuesController).exploreVenues(eq(near),
        capture(exploreVenuesControllerListener))

    val error = Throwable("Error")
    exploreVenuesControllerListener.value.onGetVenuesError(error)

    verify(getVenuesInteractorListener).onGetVenuesError(error)
  }

  private fun buildMockVenues(): List<VenueModel> = listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS)
  )

}