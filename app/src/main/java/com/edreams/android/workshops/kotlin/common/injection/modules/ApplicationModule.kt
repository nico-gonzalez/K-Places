package com.edreams.android.workshops.kotlin.common.injection.modules

import android.content.Context
import com.edreams.android.workshops.kotlin.common.KotlinWorkshopApplication
import com.edreams.android.workshops.kotlin.common.resources.AndroidResourceProvider
import com.edreams.android.workshops.kotlin.injection.presentation.MapperModule
import com.edreams.android.workshops.kotlin.injection.presentation.ViewModelModule
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import com.edreams.android.workshops.kotlin.presentation.resources.ResourceProvider
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(MapperModule::class, ViewModelModule::class))
class ApplicationModule {

  @PerApplication
  @Provides
  fun provideContext(application: KotlinWorkshopApplication): Context = application

  @PerApplication
  @Provides
  fun provideResourceProvider(context: Context): ResourceProvider = AndroidResourceProvider(context)
}