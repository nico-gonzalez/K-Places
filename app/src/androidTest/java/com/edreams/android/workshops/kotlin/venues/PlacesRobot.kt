package com.edreams.android.workshops.kotlin.venues

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.rule.ActivityTestRule
import com.edreams.android.workshops.kotlin.R.id

class PlacesRobot {

  var activityRule: ActivityTestRule<VenuesActivity> = ActivityTestRule(VenuesActivity::class.java,
      true, false)

  fun init() = apply {
    activityRule.launchActivity(null)
  }

  fun checkPlacesAreDisplayed() = apply {
    onView(ViewMatchers.withId(id.venuesRV)).check(
        matches(hasDescendant(ViewMatchers.withId(id.placeTitle))))
  }
}