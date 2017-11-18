package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.venues.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.injection.common.MapperModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = arrayOf(MapperModule::class, NetworkModule::class,
    ControllerModule::class))
abstract class RepositoryModule {

  @Singleton
  @Binds
  abstract fun provideVenuesRepository(venusDataRepository: VenuesDataRepository): VenuesRepository
}