package com.edreams.android.workshops.kotlin.util

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.edreams.android.workshops.kotlin.test.TestApplication

class KotlinWorkshopJUnitRunner : AndroidJUnitRunner() {

  override fun newApplication(cl: ClassLoader?, className: String?,
      context: Context?): Application {
    return super.newApplication(cl, TestApplication::class.java.name, context)
  }
}