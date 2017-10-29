package com.edreams.android.workshops.kotlin.util

import android.app.Activity
import android.app.Application
import com.edreams.android.workshops.kotlin.common.injection.DaggerTestApplicationComponent
import com.edreams.android.workshops.kotlin.common.injection.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector {

  @Inject lateinit var injector: DispatchingAndroidInjector<Activity>

  private lateinit var appComponent: TestApplicationComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerTestApplicationComponent
        .builder()
        .application(this)
        .build()
    appComponent.inject(this)
  }

  fun appComponent(): TestApplicationComponent {
    return appComponent
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return injector
  }
}