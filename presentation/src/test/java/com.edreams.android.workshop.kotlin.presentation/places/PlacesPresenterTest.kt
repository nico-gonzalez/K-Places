package com.edreams.android.workshop.kotlin.presentation.places

import com.edreams.android.workshops.kotlin.presentation.places.PlacesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.PlacesView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PlacesPresenterTest {

  private lateinit var presenter: PlacesPresenter

  @Mock lateinit var placesView: PlacesView

  @Before
  fun setup() {
    presenter = PlacesPresenter(placesView)
  }

  @Test
  fun `When places are loaded then interactor is called and then the view displays the result`() {
    presenter.loadPlaces()

    verify(placesView).showPlaces(anyList())
  }

  @Test
  fun `When place search is triggered then the view displays the results`() {
    presenter.onSearch("Barcelona")

    verify(placesView).showPlaces(anyList())
  }

}