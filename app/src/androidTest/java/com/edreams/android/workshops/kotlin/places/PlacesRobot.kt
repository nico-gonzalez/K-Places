package com.edreams.android.workshops.kotlin.places

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.rule.ActivityTestRule
import com.edreams.android.workshops.kotlin.R.id

class PlacesRobot {

  var activityRule: ActivityTestRule<PlacesActivity> = ActivityTestRule(PlacesActivity::class.java,
      true, false)

  fun init() = apply {
    activityRule.launchActivity(null)
  }

  fun checkPlacesAreDisplayed() = apply {
    onView(ViewMatchers.withId(id.placesRV)).check(
        matches(hasDescendant(ViewMatchers.withId(id.placeTitle))))
  }
}