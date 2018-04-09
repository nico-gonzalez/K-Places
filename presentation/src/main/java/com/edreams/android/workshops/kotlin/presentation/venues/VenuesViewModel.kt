package com.edreams.android.workshops.kotlin.presentation.venues

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenues
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenuesUiModel
import com.edreams.android.workshops.kotlin.presentation.viewmodel.SingleLiveEvent
import javax.inject.Inject

class VenuesViewModel @Inject constructor(
    private val getVenues: GetVenues,
    private val mapper: Mapper<VenueModel, VenueUiModel>,
    private val resourceProvider: ResourceProvider) : ViewModel() {

  private val venues: MutableLiveData<VenuesUiModel> = MutableLiveData()
  private val emptySearchError: MutableLiveData<String> = MutableLiveData()
  private val selectedVenue: MutableLiveData<VenueUiModel> = SingleLiveEvent()

  fun venues() = venues

  fun selectedVenue() = selectedVenue

  fun emptySearchError() = emptySearchError

  fun loadVenues(near: String): LiveData<VenuesUiModel> = venues.apply {
    value = VenuesUiModel.progress()
    getVenues.execute(near,
        { value = VenuesUiModel.success(mapper.map(it.value)) },
        { value = VenuesUiModel.error(it.error.localizedMessage) })
  }

  fun onSearch(queryString: String) = venues.apply {
    if (queryString.isEmpty()) {
      emptySearchError.value = resourceProvider.emptyVenueSearchErrorMessage()
      return@apply
    }

    value = VenuesUiModel.progress()
    getVenues.execute(queryString,
        { value = VenuesUiModel.success(mapper.map(it.value)) },
        { value = VenuesUiModel.error(it.error.localizedMessage) })
  }

  fun onVenueSelected(venue: VenueUiModel) = selectedVenue.apply {
    value = venue
  }

}
