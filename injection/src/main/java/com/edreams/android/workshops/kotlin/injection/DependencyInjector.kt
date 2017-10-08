package com.edreams.android.workshops.kotlin.injection

import com.edreams.android.workshops.kotlin.data.mapper.VenueMapper
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView

object DependencyInjector {

  private val restService: RestService? = null

  fun provideRestService(): RestService = restService ?: RestService()

  fun provideExploreVenueController(): ExploreVenuesController = ExploreVenuesNetController(
      provideRestService(), provideVenuesModelMapper())

  private fun provideVenuesModelMapper(): Mapper<VenueResponse, VenueModel> = VenueMapper()

  private fun provideVenuesUiModelMapper(): Mapper<VenueModel, VenueUiModel> = VenuesUiModelMapper()

  fun provideGetVenuesInteractor(): GetVenuesInteractor =
      GetVenuesInteractor(provideExploreVenueController())

  fun provideVenuesPresenter(venuesView: VenuesView): VenuesPresenter =
      VenuesPresenter(venuesView, provideGetVenuesInteractor(), provideVenuesUiModelMapper())
}