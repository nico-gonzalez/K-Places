package com.edreams.android.workshops.kotlin.injection

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.data.net.service.RestService
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.presentation.places.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.places.VenuesView

object DependencyInjector {

  fun provideRestService(): RestService = RestService()

  fun provideExploreVenueController(): ExploreVenuesController = ExploreVenuesNetController(
      provideRestService())

  fun provideGetVenuesInteractor(): GetVenuesInteractor =
      GetVenuesInteractor(provideExploreVenueController())

  fun provideVenuesPresenter(venuesView: VenuesView): VenuesPresenter =
      VenuesPresenter(venuesView, provideGetVenuesInteractor())
}