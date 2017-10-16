package com.edreams.android.workshops.kotlin.venues

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.edreams.android.workshops.kotlin.util.BaseTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class VenuesActivityTest : BaseTest() {

  private lateinit var venuesRobot: VenuesRobot

  @Before
  fun setup() {
    venuesRobot = VenuesRobot().init()
  }

  @Test
  fun onLoadViewShowsVenues() {
    venuesRobot.checkVenuesAreDisplayed()
        .checkProgressBarIsNotDisplayed()
  }

  @Test
  fun onVenueClickShowsDetails() {
    venuesRobot.clickOnVenue()
        .checkSelectedVenueDetailsIsDisplayed()
  }
}