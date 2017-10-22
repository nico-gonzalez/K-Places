package com.edreams.android.workshop.kotlin.presentation.places

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.inOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VenuesPresenterTest {

  private val ID = "1"
  private val NAME = "Sagrada familia"
  private val RATING = 3.5f
  private val PHOTO = "https://cdn.workshop.kotlin.com/image.png"
  private val DISTANCE = 500
  private val CHECKINS = 200
  private val TIPS = "A tip"
  private val PHONE = "+(34) 689 16 77 55"
  private val ADDRESS = listOf("Carrer de Bailen 67-69", "Barcelona", "Spain")

  private lateinit var presenter: VenuesPresenter

  @Mock private lateinit var placesView: VenuesView
  @Mock private lateinit var getVenuesInteractor: GetVenuesInteractor
  @Mock private lateinit var mapper: Mapper<VenueModel, VenueUiModel>

  @Captor private lateinit var successCaptor: ArgumentCaptor<(List<VenueModel>) -> Unit>
  @Captor private lateinit var errorCaptor: ArgumentCaptor<(Throwable) -> Unit>

  @Before
  fun setup() {
    presenter = VenuesPresenter(placesView, getVenuesInteractor, mapper)
  }

  @Test
  fun `When places are loaded then interactor is called and then the view displays the result`() {
    val near = "Barcelona"
    presenter.loadVenues(near)

    verify(placesView).showLoading()
    verify(getVenuesInteractor).getVenues(eq(near), capture(successCaptor), capture(errorCaptor))

    val venues = buildMockVenues()
    successCaptor.value.invoke(venues)

    inOrder(placesView) {
      verify(placesView).hideLoading()
      verify(placesView).showVenues(anyList())
    }
  }

  @Test
  fun `When place search is triggered then the view displays the results`() {
    val near = "Barcelona"
    presenter.onSearch(near)

    verify(placesView).showLoading()
    verify(getVenuesInteractor).getVenues(eq(near), capture(successCaptor), capture(errorCaptor))

    val venues = buildMockVenues()
    successCaptor.value.invoke(venues)

    inOrder(placesView) {
      verify(placesView).hideLoading()
      verify(placesView).showVenues(anyList())
    }
  }

  private fun buildMockVenues() = listOf(
      VenueModel(ID, NAME, RATING, PHOTO, PHONE, DISTANCE, ADDRESS, CHECKINS, TIPS)
  )
}