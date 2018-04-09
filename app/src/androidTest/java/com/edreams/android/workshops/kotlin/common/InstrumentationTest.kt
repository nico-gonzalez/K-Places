package com.edreams.android.workshops.kotlin.common

import android.support.test.InstrumentationRegistry
import com.edreams.android.workshops.kotlin.util.TestApplication

abstract class InstrumentationTest {

  val application by lazy {
    InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
  }
}