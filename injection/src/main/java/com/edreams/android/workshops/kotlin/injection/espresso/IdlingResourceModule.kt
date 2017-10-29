package com.edreams.android.workshops.kotlin.injection.espresso

import android.support.test.espresso.idling.CountingIdlingResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class IdlingResourceModule {

  @Singleton
  @Provides
  fun provideIdlingResource(): CountingIdlingResource = CountingIdlingResource(
      "CustomIdlingResource")
}