package com.edreams.android.workshops.kotlin.util

import android.support.test.espresso.IdlingRegistry
import com.edreams.android.workshops.kotlin.injection.DependencyInjector.provideIdlingResource
import org.junit.After
import org.junit.Before

open class BaseTest {

  @Before
  fun register() {
    IdlingRegistry.getInstance().register(provideIdlingResource())
  }

  @After
  fun unregister() {
    IdlingRegistry.getInstance().unregister(provideIdlingResource())
  }
}