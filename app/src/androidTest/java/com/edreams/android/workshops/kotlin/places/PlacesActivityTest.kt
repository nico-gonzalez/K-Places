package com.edreams.android.workshops.kotlin.places

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PlacesActivityTest {

  private lateinit var placesRobot: PlacesRobot

  @Before
  fun setup() {
    placesRobot = PlacesRobot().init()
  }

  @Test
  fun onLoadViewShowsPlacesCachedPlaces() {
    placesRobot.checkPlacesAreDisplayed()
  }
}