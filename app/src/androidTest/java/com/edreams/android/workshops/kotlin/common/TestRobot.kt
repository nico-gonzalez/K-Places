package com.edreams.android.workshops.kotlin.util

import android.support.test.InstrumentationRegistry

open class TestRobot {

  fun application(): TestApplication = InstrumentationRegistry.getTargetContext()
      .applicationContext as TestApplication
}