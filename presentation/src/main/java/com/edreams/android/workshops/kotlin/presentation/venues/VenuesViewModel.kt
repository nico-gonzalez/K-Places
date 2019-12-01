package com.edreams.android.workshops.kotlin.presentation.venues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenueDetails
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenues
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueDetailsUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenuesUiModel
import com.edreams.android.workshops.kotlin.presentation.viewmodel.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class VenuesViewModel @Inject constructor(
    private val getVenues: GetVenues,
    private val getVenueDetails: GetVenueDetails,
    private val mapper: Mapper<VenueModel, VenueUiModel>,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _venues: MutableLiveData<VenuesUiModel> = MutableLiveData()
    private val _emptySearchError: MutableLiveData<String> = MutableLiveData()
    private val _selectedVenue: MutableLiveData<VenueDetailsUiModel> = SingleLiveEvent()

    val venues = _venues
    val selectedVenue = _selectedVenue
    val emptySearchError = _emptySearchError

    fun loadVenues(near: String): LiveData<VenuesUiModel> = _venues.apply {
        value = VenuesUiModel.progress()
        getVenues.execute(near).onEach {
            value = if (it.isSuccess)
                VenuesUiModel.success(mapper.map(it.value!!))
            else
                VenuesUiModel.error(it.error?.localizedMessage ?: "")
        }.launchIn(viewModelScope)
    }

    fun onSearch(queryString: String) = _venues.apply {
        if (queryString.isEmpty()) {
            _emptySearchError.value = resourceProvider.emptyVenueSearchErrorMessage()
            return@apply
        }

        value = VenuesUiModel.progress()
        getVenues.execute(queryString)
            .onEach {
                value = if (it.isSuccess)
                    VenuesUiModel.success(mapper.map(it.value!!))
                else
                    VenuesUiModel.error(it.error?.localizedMessage ?: "")
            }
            .launchIn(viewModelScope)
    }

    fun onVenueSelected(venue: VenueUiModel) = _selectedVenue.run {
        value = VenueDetailsUiModel.progress()
        getVenueDetails.execute(venue.id)
            .onEach {
                value = if (it.isSuccess)
                    VenueDetailsUiModel.success(mapper.map(it.value!!))
                else
                    VenueDetailsUiModel.error(it.error?.localizedMessage ?: "")
            }
            .launchIn(viewModelScope)
    }
}
