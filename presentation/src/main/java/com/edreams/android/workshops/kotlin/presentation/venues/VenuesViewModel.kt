package com.edreams.android.workshops.kotlin.presentation.venues

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.model.VenuesUiModel
import com.edreams.android.workshops.kotlin.presentation.viewmodel.SingleLiveEvent
import javax.inject.Inject

class VenuesViewModel @Inject constructor(
    private val getVenuesInteractor: GetVenuesInteractor,
    private val mapper: VenuesUiModelMapper) : ViewModel() {
    private val resourceProvider: ResourceProvider) : ViewModel() {

  private val venues: MutableLiveData<VenuesUiModel> = MutableLiveData()
  private val emptySearchError: MutableLiveData<String> = MutableLiveData()
  private val selectedVenue: MutableLiveData<VenueUiModel> = SingleLiveEvent()

  fun loadVenues(near: String): LiveData<VenuesUiModel> = venues.apply {
    value = VenuesUiModel.progress()
    getVenuesInteractor.getVenues(near, { value = VenuesUiModel.success(mapper.map(it)) },
        { value = VenuesUiModel.error(it.localizedMessage) })
  }

  fun onSearch(queryString: String) = venues.apply {
    if (queryString.isEmpty()) {
      emptySearchError.value = resourceProvider.emptyVenueSearchErrorMessage()
      return@apply
    }

    value = VenuesUiModel.progress()
    getVenuesInteractor.getVenues(queryString, { value = VenuesUiModel.success(mapper.map(it)) },
        { value = VenuesUiModel.error(it.localizedMessage) })
  }

  fun getVenueSelected() = selectedVenue

  fun onVenueSelected(venue: VenueUiModel) = selectedVenue.apply {
    value = venue
  }

  fun getEmptySearchError() = emptySearchError
}
