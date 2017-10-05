package com.edreams.android.workshops.kotlin.places

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.presentation.places.PlaceViewModel
import com.edreams.android.workshops.kotlin.presentation.places.PlacesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.PlacesView
import kotlinx.android.synthetic.main.activity_places.placesRV

class PlacesActivity : AppCompatActivity(), PlacesView {

  private val adapter: PlacesAdapter by lazy {
    PlacesAdapter()
  }

  private val presenter: PlacesPresenter by lazy {
    PlacesPresenter(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_places)

    setupPlacesList()

    presenter.loadPlaces()
  }

  private fun setupPlacesList() = with(placesRV) {
    adapter = this@PlacesActivity.adapter
    layoutManager = LinearLayoutManager(this@PlacesActivity)
    setHasFixedSize(true)
  }

  override fun showPlaces(places: List<PlaceViewModel>) {
    adapter.setPlaces(places)
  }
}
