package com.edreams.android.workshops.kotlin.venues

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast.LENGTH_SHORT
import com.edreams.android.workshops.kotlin.R
import com.edreams.android.workshops.kotlin.R.layout
import com.edreams.android.workshops.kotlin.common.extensions.gone
import com.edreams.android.workshops.kotlin.common.extensions.load
import com.edreams.android.workshops.kotlin.common.extensions.textString
import com.edreams.android.workshops.kotlin.injection.DependencyInjector.provideVenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import kotlinx.android.synthetic.main.activity_venues.near
import kotlinx.android.synthetic.main.activity_venues.progressBar
import kotlinx.android.synthetic.main.activity_venues.search
import kotlinx.android.synthetic.main.activity_venues.venuesList
import kotlinx.android.synthetic.main.venue_details.view.venueAddress
import kotlinx.android.synthetic.main.venue_details.view.venueImage
import kotlinx.android.synthetic.main.venue_details.view.venuePhone
import kotlinx.android.synthetic.main.venue_details.view.venueTitle

class VenuesActivity : AppCompatActivity(), VenuesView {

  private val adapter: VenuesAdapter by lazy {
    VenuesAdapter({ _, venue -> presenter.onVenueSelected(venue) })
  }

  private val presenter: VenuesPresenter by lazy {
    provideVenuesPresenter(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_venues)

    setupVenuesList()
    setupSearch()

    presenter.loadVenues("Barcelona")
  }

  private fun setupSearch() {
    search.setOnClickListener {
      presenter.onSearch(near.textString)
    }
  }

  private fun setupVenuesList() = with(venuesList) {
    adapter = this@VenuesActivity.adapter
    layoutManager = LinearLayoutManager(this@VenuesActivity)
    setHasFixedSize(true)
  }

  override fun showVenues(venues: List<VenueUiModel>) {
    adapter.setPlaces(venues)
  }

  override fun showError(error: String) {
    Snackbar.make(venuesList, error, LENGTH_SHORT)
        .show()
  }

  override fun showLoading() {
    progressBar.show()
  }

  override fun hideLoading() {
    progressBar.hide()
  }

  override fun showEmptySearchError() {
    near.error = getString(R.string.empty_search_message)
  }

  override fun showVenueDetails(venue: VenueUiModel) {
    val bottomSheet = BottomSheetDialog(this)
    val bottomSheetView = layoutInflater.inflate(R.layout.venue_details, null)
    bottomSheet.setContentView(bottomSheetView)
    with(bottomSheetView) {
      venueTitle.text = venue.title
      venueImage.load(venue.photoUrl)
      venue.formattedAddress?.let {
        venueAddress.text = venue.formattedAddress
      } ?: run {
        venueAddress.gone()
      }
      venue.formattedPhone?.let {
        venuePhone.text = venue.formattedPhone
      } ?: run {
        venuePhone.gone()
      }
    }
    bottomSheet.show()
  }
}
