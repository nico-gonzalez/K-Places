package com.edreams.android.workshops.kotlin.domain.venues

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify

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

  private val venuesRepository: VenuesRepository = mock()
  private val success: (List<VenueModel>) -> Unit = mock()
  private val error: (Throwable) -> Unit = mock()

  private val successCaptor: KArgumentCaptor<(List<VenueModel>) -> Unit> = argumentCaptor()
  private val errorCaptor: KArgumentCaptor<(Throwable) -> Unit> = argumentCaptor()

  private lateinit var interactor: GetVenuesInteractor

  @Before
  fun setup() {
    interactor = GetVenuesInteractor(venuesRepository)
  }

  @Test
  fun `When getVenues success calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, success, error)

    verify(venuesRepository).getVenues(eq(near), successCaptor.capture(),
        errorCaptor.capture())

    val venues = buildMockVenues()
    successCaptor.lastValue(venues)

    verify(success).invoke(venues)
  }

  @Test
  fun `When getVenues error calls explore venues net controller and forwards result to listener`() {
    val near = "Barcelona"
    interactor.getVenues(near, success, error)

    verify(venuesRepository).getVenues(
        eq(near),
        successCaptor.capture(),
        errorCaptor.capture())

    val error = Throwable("Error")
    errorCaptor.lastValue(error)

    verify(this.error).invoke(error)
  }

  private fun buildMockVenues(): List<VenueModel> = listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS)
  )

}