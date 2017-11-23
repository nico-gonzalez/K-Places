package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesNetController
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ControllerModule {

  @Singleton
  @Binds
  abstract fun bindExploreVenuesController(
      exploreVenuesNetController: ExploreVenuesNetController): ExploreVenuesController

}