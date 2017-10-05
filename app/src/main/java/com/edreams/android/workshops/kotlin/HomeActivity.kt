package com.edreams.android.workshops.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.edreams.android.workshops.kotlin.injection.DependencyInjector

class HomeActivity : AppCompatActivity() {

  private val injector = DependencyInjector()
  private val presenter = injector.provideTestServidePresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    presenter.getVenues()
  }
}
