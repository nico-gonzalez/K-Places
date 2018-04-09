package com.edreams.android.workshops.kotlin.venues

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView.ViewHolder
import com.edreams.android.workshops.kotlin.R
import com.edreams.android.workshops.kotlin.R.id
import com.edreams.android.workshops.kotlin.util.TestRobot
import org.hamcrest.Matchers.not

fun venues(func: VenuesRobot.() -> Unit) = VenuesRobot().apply { func() }

fun venueDetails(func: DetailsRobot.() -> Unit) = DetailsRobot().apply { func() }

class DetailsRobot : TestRobot() {
  fun isVisible() {
    onView(withId(R.id.venueTitle)).check(matches(isDisplayed()))
  }
}

class VenuesRobot : TestRobot() {

  private var activityRule: ActivityTestRule<VenuesActivity> = ActivityTestRule(
      VenuesActivity::class.java, true, false)

  init {
    activityRule.launchActivity(null)
  }

  fun isVisible() {
    onView(withId(id.venuesList))
        .check(matches(hasDescendant(withId(id.placeTitle))))
  }

  fun isNotLoading() {
    onView(withId(id.progressBar))
        .check(matches(not(isDisplayed())))
  }

  fun selectVenue(): DetailsRobot {
    onView(withId(R.id.venuesList)).perform(actionOnItemAtPosition<ViewHolder>(1, click()))
    return DetailsRobot()
  }
}