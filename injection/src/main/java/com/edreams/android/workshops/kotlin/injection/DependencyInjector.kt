package com.edreams.android.workshops.kotlin.injection

import android.support.test.espresso.idling.CountingIdlingResource
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.data.mapper.VenueMapper
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.data.response.VenueResponse
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.domain.mapper.Mapper
import com.edreams.android.workshops.kotlin.domain.model.VenueModel
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.VenueUiModel
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView

object DependencyInjector {

  private var restService: RestService = RestService()
  private var idlingResource: CountingIdlingResource = CountingIdlingResource(
      "CustomIdlingResource")

  fun provideRestService(): RestService = restService

  private fun provideFoursquareService() = provideRestService().service

  private fun provideExploreVenueController(): ExploreVenuesController = ExploreVenuesNetController(
      provideFoursquareService(), provideIdlingResource())

  private fun provideVenuesRepository(): VenuesRepository = VenuesDataRepository(
      provideVenuesModelMapper(), provideExploreVenueController())

  private fun provideVenuesModelMapper(): Mapper<VenueResponse, VenueModel> = VenueMapper()

  private fun provideVenuesUiModelMapper(): Mapper<VenueModel, VenueUiModel> = VenuesUiModelMapper()

  private fun provideGetVenuesInteractor(): GetVenuesInteractor =
      GetVenuesInteractor(provideVenuesRepository())

  fun provideVenuesPresenter(venuesView: VenuesView): VenuesPresenter =
      VenuesPresenter(venuesView, provideGetVenuesInteractor(), provideVenuesUiModelMapper())

  fun provideIdlingResource(): CountingIdlingResource = idlingResource
}