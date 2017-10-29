package com.edreams.android.workshops.kotlin.common.injection.modules

import com.edreams.android.workshops.kotlin.injection.presentation.PresenterModule
import com.edreams.android.workshops.kotlin.injection.scopes.PerActivity
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesView
import com.edreams.android.workshops.kotlin.venues.VenuesActivity
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(PresenterModule::class))
class VenuesActivityModule {

  @PerActivity
  @Provides
  fun provideVenuesView(venuesActivity: VenuesActivity): VenuesView = venuesActivity

}