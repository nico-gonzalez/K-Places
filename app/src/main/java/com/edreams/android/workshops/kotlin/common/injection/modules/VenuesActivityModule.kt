package com.edreams.android.workshops.kotlin.common.injection.modules

import com.edreams.android.workshops.kotlin.common.injection.scopes.PerActivity
import com.edreams.android.workshops.kotlin.domain.interactor.GetVenuesInteractor
import com.edreams.android.workshops.kotlin.presentation.mapper.VenuesUiModelMapper
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesPresenter
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import com.edreams.android.workshops.kotlin.venues.VenuesActivity
import dagger.Module
import dagger.Provides

@Module
class VenuesActivityModule {

  @PerActivity
  @Provides
  fun provideVenuesView(venuesActivity: VenuesActivity): VenuesView = venuesActivity

  @PerActivity
  @Provides
  fun provideVenuesPresenter(venusView: VenuesView,
      getVenuesInteractor: GetVenuesInteractor,
      mapper: VenuesUiModelMapper): VenuesPresenter = VenuesPresenter(venusView,
      getVenuesInteractor, mapper)

}