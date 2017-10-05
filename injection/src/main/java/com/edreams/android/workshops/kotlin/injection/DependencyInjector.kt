package com.edreams.android.workshops.kotlin.injection

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.domain.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.presentation.presenter.GetVenuesPresenter

class DependencyInjector {

  fun provideExploreVenueController(): ExploreVenuesController {
    return ExploreVenuesNetController()
  }

  fun provideTestServiceInteractor() : GetVenuesInteractor {
    return GetVenuesInteractor(provideExploreVenueController())
  }

  fun provideTestServidePresenter() : GetVenuesPresenter {
    return GetVenuesPresenter(provideTestServiceInteractor())
  }
}