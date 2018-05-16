package com.edreams.android.workshops.kotlin.common

import android.support.test.InstrumentationRegistry

abstract class InstrumentationTest {

  val application by lazy {
    InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
  }
}