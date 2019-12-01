package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesDatasource
import com.edreams.android.workshops.kotlin.data.venues.remote.ExploreVenuesApiDatasource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ControllerModule {

  @Singleton
  @Binds
  abstract fun bindExploreVenuesController(
      exploreVenuesApiDatasource: ExploreVenuesApiDatasource): ExploreVenuesDatasource

}