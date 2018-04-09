package com.edreams.android.workshops.kotlin.domain.venues

import com.edreams.android.workshops.kotlin.domain.common.Callback
import com.edreams.android.workshops.kotlin.domain.common.GetVenuesResult
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenues
import com.edreams.android.workshops.kotlin.domain.interactor.Result
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify


class GetVenuesUseCaseTest {
  private val ID = "1"
  private val NAME = "Sagrada familia"
  private val RATING = 3.5f
  private val PHOTO = "https://cdn.workshop.kotlin.com/image.png"
  private val DISTANCE = 500
  private val CHECKINS = 200
  private val TIPS = "A tip"
  private val PHONE = "+(34) 689 16 77 55"
  private val ADDRESS = listOf("Carrer de Bailen 67-69", "Barcelona", "Spain")
  private val QUERY = "Barcelona"

  private val venuesRepository: VenuesRepository = mock()
  private val success: Callback<GetVenuesResult> = mock()
  private val error: Callback<GetVenuesResult> = mock()

  private lateinit var getVenues: GetVenues

  @Before
  fun setup() {
    getVenues = GetVenues(venuesRepository, BlockingExecutor())
  }

  @Test
  fun `When getVenues success calls explore venues net controller and forwards result to listener`() =
      runBlocking {
        val near = "Barcelona"
        val result = buildMockVenues()
        whenever(venuesRepository.getVenues(eq(near))) doReturn produce { send(result.value) }

        getVenues.execute(near, success, error)

        verify(venuesRepository).getVenues(eq(near))
        verify(success).invoke(argThat {
          value == result.value
        })
      }

  @Test
  fun `When getVenues error calls explore venues net controller and forwards result to listener`() = runBlocking {
    val near = "Barcelona"
    val errorMessage = Throwable("No venues where found")
    val result = Result(emptyList<VenueModel>(),
        error = errorMessage)
    whenever(venuesRepository.getVenues(eq(near))) doReturn produce { send(result.value) }

    getVenues.execute(near, success, error)

    verify(venuesRepository).getVenues(eq(near))
    verify(error).invoke(argThat {
      error.message == errorMessage.message
    })
  }

  private fun buildMockVenues() = GetVenuesResult(listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS, QUERY))
  )

}