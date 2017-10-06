package com.edreams.android.workshop.kotlin.presentation.places

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor.GetVenuesInteractorListener
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.places.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.VenuesView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlacesPresenterTest {

  private lateinit var presenter: VenuesPresenter

  @Mock private lateinit var placesView: VenuesView
  @Mock private lateinit var getVenuesInteractor: GetVenuesInteractor

  @Captor
  private lateinit var getVenuesInteractorListenerCaptor: ArgumentCaptor<GetVenuesInteractorListener>

  @Before
  fun setup() {
    presenter = VenuesPresenter(placesView, getVenuesInteractor)
  }

  @Test
  fun `When places are loaded then interactor is called and then the view displays the result`() {
    val near = "Barcelona"
    presenter.loadVenues()

    verify(getVenuesInteractor).getVenues(ArgumentMatchers.eq(near),
        getVenuesInteractorListenerCaptor.capture())

    val venues = with(mutableListOf<VenueModel>()) {
      add(VenueModel("1", "Barcelona", 3.4, ""))
      this
    }
    getVenuesInteractorListenerCaptor.value.onGetVenuesSuccessful(venues)


    verify(placesView).showVenues(anyList())
  }

  @Test
  fun `When place search is triggered then the view displays the results`() {
    val near = "Barcelona"
    presenter.onSearch(near)

    verify(getVenuesInteractor).getVenues(ArgumentMatchers.eq(near),
        getVenuesInteractorListenerCaptor.capture())

    val venues = with(mutableListOf<VenueModel>()) {
      add(VenueModel("1", "Barcelona", 3.4, ""))
      this
    }
    getVenuesInteractorListenerCaptor.value.onGetVenuesSuccessful(venues)


    verify(placesView).showVenues(anyList())
  }

}