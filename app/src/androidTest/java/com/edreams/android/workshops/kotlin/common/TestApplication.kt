package com.edreams.android.workshops.kotlin.util

import android.app.Activity
import android.app.Application
import com.edreams.android.workshops.kotlin.common.injection.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var injector: DispatchingAndroidInjector<Activity>

  var appComponent: TestApplicationComponent? = null
    set(value) {
      field = field ?: value.apply { this?.inject(this@TestApplication) }
    }

  override fun activityInjector(): AndroidInjector<Activity> {
    return injector
  }
}