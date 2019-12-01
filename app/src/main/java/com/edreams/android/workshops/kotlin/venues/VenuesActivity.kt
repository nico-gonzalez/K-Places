package com.edreams.android.workshops.kotlin.venues

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.edreams.android.workshops.kotlin.R
import com.edreams.android.workshops.kotlin.common.extensions.gone
import com.edreams.android.workshops.kotlin.common.extensions.load
import com.edreams.android.workshops.kotlin.common.extensions.textString
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesViewModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
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

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: VenuesViewModel

    private lateinit var adapter: VenuesAdapter

    @Inject
    internal fun setAdapter(venuesAdapter: VenuesAdapter) {
        adapter = venuesAdapter
        adapter.setVenueItemClickListener { _, venue ->
            viewModel.onVenueSelected(venue)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(VenuesViewModel::class.java)
        setContentView(R.layout.activity_venues)

        setupVenuesList()
        setupSearch()
        subscribe()

        savedInstanceState ?: viewModel.loadVenues("Barcelona")
    }

    private fun subscribe() {
        viewModel.venues.observe(this, Observer {
            it?.let {
                if (it.progress) {
                    showLoading()
                } else {
                    hideLoading()
                    if (it.error.isEmpty()) {
                        showVenues(it.venues)
                    } else {
                        showGetVenuesError(it.error)
                    }
                }
            }
        })
        viewModel.emptySearchError.observe(this, Observer {
            it?.let { showEmptySearchError(it) }
        })
        viewModel.selectedVenue.observe(this, Observer {
            it?.let {
                if (it.progress) {
                    showLoading()
                } else {
                    hideLoading()
                    if (it.error.isEmpty()) {
                        showVenueDetails(it.venue!!)
                    } else {
                        showVenueDetailsError(it.error)
                    }
                }
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

    private fun showGetVenuesError(error: String) {
        adapter.setPlaces(emptyList())
        Snackbar.make(venuesList, error, Snackbar.LENGTH_SHORT)
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

    private fun showVenueDetails(venueDetails: VenueUiModel) {
        venueDetails.run {
            val bottomSheet = BottomSheetDialog(this@VenuesActivity)
            val bottomSheetView = layoutInflater.inflate(R.layout.venue_details, null)
            bottomSheet.setContentView(bottomSheetView)
            bottomSheetView.apply {
                venueTitle.text = venueDetails.title
                venueImage.load(venueDetails.photoUrl)
                venueDetails.formattedAddress?.let {
                    venueAddress.text = venueDetails.formattedAddress
                } ?: venueAddress.gone()

                venueDetails.formattedPhone?.let {
                    venuePhone.text = venueDetails.formattedPhone
                } ?: venuePhone.gone()
            }
            bottomSheet.show()
        }
    }

    private fun showVenueDetailsError(error: String) {
        Snackbar.make(venuesList, error, Snackbar.LENGTH_SHORT)
            .show()
    }
}
