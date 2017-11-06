package com.edreams.android.workshops.kotlin.venues

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
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
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_venues.near
import kotlinx.android.synthetic.main.activity_venues.progressBar
import kotlinx.android.synthetic.main.activity_venues.search
import kotlinx.android.synthetic.main.activity_venues.venuesList
import kotlinx.android.synthetic.main.venue_details.view.venueAddress
import kotlinx.android.synthetic.main.venue_details.view.venueImage
import kotlinx.android.synthetic.main.venue_details.view.venuePhone
import kotlinx.android.synthetic.main.venue_details.view.venueTitle
import javax.inject.Inject

class VenuesActivity : AppCompatActivity() {

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private lateinit var viewModel: VenuesViewModel

  lateinit var adapter: VenuesAdapter

  @Inject internal fun setAdapter(venuesAdapter: VenuesAdapter) {
    adapter = venuesAdapter
    adapter.setVenueItemClickListener { _, venue ->
      viewModel.onVenueSelected(venue)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(VenuesViewModel::class.java)
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_venues)

    setupVenuesList()
    setupSearch()

    subscribe()
  }


  private fun subscribe() {
    viewModel.loadVenues("Barcelona").observe(this, Observer {
      it?.let {
        if (it.progress) {
          showLoading()
        } else {
          hideLoading()
          if (it.error.isEmpty()) {
            showVenues(it.venues)
          } else {
            showError(it.error)
          }
        }
      }
    })
    viewModel.getEmptySearchError().observe(this, Observer {
      it?.let {
        showEmptySearchError(it)
      }
    })
    viewModel.getVenueSelected().observe(this, Observer {
      it?.let {
        showVenueDetails(it)
      }
    })
  }

  private fun setupSearch() {
    search.setOnClickListener {
      viewModel.onSearch(near.textString)
    }
  }

  private fun setupVenuesList() = with(venuesList) {
    adapter = this@VenuesActivity.adapter
    layoutManager = LinearLayoutManager(this@VenuesActivity)
    setHasFixedSize(true)
  }

  private fun showVenues(venues: List<VenueUiModel>) {
    adapter.setPlaces(venues)
  }

  private fun showError(error: String) {
    Snackbar.make(venuesList, error, LENGTH_SHORT)
        .show()
  }

  private fun showLoading() {
    progressBar.show()
  }

  private fun hideLoading() {
    progressBar.hide()
  }

  private fun showEmptySearchError(message: String) {
    near.error = message
  }

  private fun showVenueDetails(venue: VenueUiModel) {
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
