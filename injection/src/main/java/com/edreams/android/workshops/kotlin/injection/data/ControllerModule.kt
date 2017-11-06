package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesController
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
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