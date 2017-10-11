package com.edreams.android.workshop.kotlin.presentation.places

import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VenuesPresenterTest {

  @Mock private lateinit var placesView: VenuesView
  @Mock private lateinit var mapper: Mapper<VenueModel, VenueUiModel>

  @Before
  fun setup() {
  }

  @Test
  fun `When places are loaded then interactor is called and then the view displays the result`() {
    TODO("Unimplemented")
  }
}