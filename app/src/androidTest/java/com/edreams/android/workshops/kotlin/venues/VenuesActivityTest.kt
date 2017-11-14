package com.edreams.android.workshops.kotlin.venues

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class VenuesActivityTest {

  @Test
  fun onLoadViewShowsVenues() {
    venues {
      isVisible()
      isNotLoading()
    }
  }

  @Test
  fun onVenueClickShowsDetails() {
    venues {
      isVisible()
      selectVenue()
    }
    venueDetails {
      isVisible()
    }
  }
}