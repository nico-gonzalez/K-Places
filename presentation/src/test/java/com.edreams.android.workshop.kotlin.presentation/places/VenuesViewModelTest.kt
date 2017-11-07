package com.edreams.android.workshop.kotlin.presentation.places

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesViewModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class VenuesViewModelTest {

  @Rule
  @JvmField
  val rule = InstantTaskExecutorRule()

  private val ID = "1"
  private val NAME = "Sagrada familia"
  private val RATING = 3.5f
  private val PHOTO = "https://cdn.workshop.kotlin.com/image.png"
  private val DISTANCE = 500
  private val CHECKINS = 200
  private val TIPS = "A tip"
  private val PHONE = "+(34) 689 16 77 55"
  private val ADDRESS = listOf("Carrer de Bailen 67-69", "Barcelona", "Spain")

  private lateinit var viewModel: VenuesViewModel

  private val getVenuesInteractor: GetVenuesInteractor = mock()
  private val mapper: VenuesUiModelMapper = mock()
  private val resourceProvider: ResourceProvider = mock()

  private val successCaptor: KArgumentCaptor<(List<VenueModel>) -> Unit> = argumentCaptor()
  private val errorCaptor: KArgumentCaptor<(Throwable) -> Unit> = argumentCaptor()

  @Before
  fun setup() {
    viewModel = VenuesViewModel(getVenuesInteractor, mapper, resourceProvider)
  }

  @Test
  fun whenPlaceSearchQueryIsEmptyThenAnErrorIsEmitted() {
    whenever(resourceProvider.emptyVenueSearchErrorMessage())
        .thenReturn("Empty search error message")

    val emptySearchErrorLiveData = viewModel.getEmptySearchError()
    viewModel.onSearch("")

    verify(resourceProvider, times(1)).emptyVenueSearchErrorMessage()
    assertThat(emptySearchErrorLiveData.value, `is`(equalTo("Empty search error message")))
  }

  @Test
  fun whenPlacesFailToLoadThenTheInteractorIsCalledAndAnErrorIsEmitted() {
    val near = "Barcelona"
    val venuesLiveData = viewModel.loadVenues(near)

    assertThat(venuesLiveData.value?.progress, `is`(true))

    argumentCaptor<(Throwable) -> Unit>().apply {
      verify(getVenuesInteractor).getVenues(eq(near), successCaptor.capture(), capture())
      lastValue(Throwable("An error occurred"))
    }

    assertThat(venuesLiveData.value?.progress, `is`(false))
    assertThat(venuesLiveData.value?.error, `is`(equalTo("An error occurred")))
  }

  @Test
  fun whenPlacesAreLoadedThenInteractorIsCalledAndThenAListOfVenuesIsEmitted() {
    val near = "Barcelona"
    val venuesLiveData = viewModel.loadVenues(near)

    assertThat(venuesLiveData.value?.progress, `is`(true))

    val venues = buildMockVenues()
    val venuesUi = buildMockVenuesUi()
    whenever(mapper.map(venues)).thenReturn(venuesUi)

    argumentCaptor<(List<VenueModel>) -> Unit>().apply {
      verify(getVenuesInteractor).getVenues(eq(near), capture(), errorCaptor.capture())
      lastValue(venues)
    }

    assertThat(venuesLiveData.value?.progress, `is`(false))
    assertThat(venuesLiveData.value?.venues, `is`(equalTo(venuesUi)))
  }

  @Test
  fun whenPlaceSearchIsTriggeredThenAListOfVenuesIsEmitted() {
    val near = "Barcelona"
    val venuesLiveData = viewModel.onSearch(near)

    assertThat(venuesLiveData.value?.progress, `is`(true))

    val venues = buildMockVenues()
    val venuesUi = buildMockVenuesUi()
    whenever(mapper.map(venues)).thenReturn(venuesUi)

    argumentCaptor<(List<VenueModel>) -> Unit>().apply {
      verify(getVenuesInteractor).getVenues(eq(near), capture(), errorCaptor.capture())
      lastValue(venues)
    }

    assertThat(venuesLiveData.value?.progress, `is`(false))
    assertThat(venuesLiveData.value?.venues, `is`(equalTo(venuesUi)))
  }

  private fun buildMockVenues() = listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS)
  )

  private fun buildMockVenuesUi() = listOf(
      VenueUiModel(ID, NAME, RATING, PHOTO, PHONE)
  )
}