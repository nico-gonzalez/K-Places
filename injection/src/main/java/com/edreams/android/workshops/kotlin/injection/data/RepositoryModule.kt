package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.mapper.VenueMapper
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class RepositoryModule {

  @Singleton
  @Provides
  fun provideVenuesRepository(
      mapper: VenueMapper,
      exploreVenuesController: ExploreVenuesNetController): VenuesRepository = VenuesDataRepository(
      mapper, exploreVenuesController)
}