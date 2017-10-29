package com.edreams.android.workshops.kotlin.common.injection

import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.test.injection.mock.repository.VenuesMockRepository
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule {

  @Provides
  fun provideVenuesRepository(): VenuesRepository = VenuesMockRepository()
}