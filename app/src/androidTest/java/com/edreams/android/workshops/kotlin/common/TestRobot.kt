package com.edreams.android.workshops.kotlin.common

import android.support.test.InstrumentationRegistry

open class TestRobot {

  fun application(): TestApplication = InstrumentationRegistry.getTargetContext()
      .applicationContext as TestApplication
}