package com.edreams.android.workshops.kotlin.common.injection.modules

import android.content.Context
import com.edreams.android.workshops.kotlin.common.KotlinWorkshopApplication
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

  @PerApplication
  @Provides
  fun provideContext(application: KotlinWorkshopApplication): Context = application
}