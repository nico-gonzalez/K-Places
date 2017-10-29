package com.edreams.android.workshops.kotlin.common

import android.app.Activity
import android.app.Application
import com.edreams.android.workshops.kotlin.common.injection.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class KotlinWorkshopApplication : Application(), HasActivityInjector {

  @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    DaggerApplicationComponent
        .builder()
        .application(this)
        .build()
        .inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return activityDispatchingAndroidInjector
  }

}