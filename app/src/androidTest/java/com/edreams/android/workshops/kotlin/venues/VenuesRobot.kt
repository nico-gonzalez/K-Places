package com.edreams.android.workshops.kotlin.venues

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.edreams.android.workshops.kotlin.R.id

class VenuesRobot {

  private var activityRule: ActivityTestRule<VenuesActivity> = ActivityTestRule(
      VenuesActivity::class.java, true, false)

  fun init() = apply {
    activityRule.launchActivity(null)
  }


  fun checkVenuesAreDisplayed() = apply {
    onView(withId(id.venuesList))
        .check(matches(hasDescendant(withId(id.placeTitle))))
  }
}