package com.edreams.android.workshops.kotlin.injection.presentation

import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.injection.scopes.PerActivity
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

  @PerActivity
  @Provides
  fun provideVenuesPresenter(venusView: VenuesView,
      getVenuesInteractor: GetVenuesInteractor,
      mapper: VenuesUiModelMapper): VenuesPresenter = VenuesPresenter(venusView,
      getVenuesInteractor, mapper)
}