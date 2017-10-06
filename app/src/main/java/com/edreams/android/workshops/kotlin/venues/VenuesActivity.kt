package com.edreams.android.workshops.kotlin.venues

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast.LENGTH_SHORT
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.injection.DependencyInjector.provideVenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.VenueViewModel
import com.edreams.android.workshops.kotlin.presentation.places.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.VenuesView
import kotlinx.android.synthetic.main.activity_venues.progressBar
import kotlinx.android.synthetic.main.activity_venues.venuesRV

class VenuesActivity : AppCompatActivity(), VenuesView {

  private val adapter: VenuesAdapter by lazy {
    VenuesAdapter()
  }

  private val presenter: VenuesPresenter by lazy {
    provideVenuesPresenter(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_venues)

    setupVenuesList()

    presenter.loadVenues()
  }

  private fun setupVenuesList() = with(venuesRV) {
    adapter = this@VenuesActivity.adapter
    layoutManager = LinearLayoutManager(this@VenuesActivity)
    setHasFixedSize(true)
  }

  override fun showVenues(
      venues: List<VenueViewModel>) {
    adapter.setPlaces(venues)
  }

  override fun showError(error: String) {
    Snackbar.make(venuesRV, error, LENGTH_SHORT)
        .show()
  }

  override fun showLoading() {
    progressBar.show()
  }

  override fun hideLoading() {
    progressBar.hide()
  }
}
