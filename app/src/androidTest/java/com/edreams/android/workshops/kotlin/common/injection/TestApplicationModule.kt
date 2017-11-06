package com.edreams.android.workshops.kotlin.common.injection

import android.content.Context
import com.edreams.android.workshops.kotlin.common.resources.AndroidResourceProvider
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import com.edreams.android.workshops.kotlin.test.injection.mock.repository.VenuesMockRepository
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule {

  @Provides
  fun provideVenuesRepository(): VenuesRepository = VenuesMockRepository()

  @PerApplication
  @Provides
  fun provideResourceProvider(context: Context): ResourceProvider = AndroidResourceProvider(context)
}