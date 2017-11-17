package com.edreams.android.workshops.kotlin.common.injection

import android.app.Application
import com.edreams.android.workshops.kotlin.common.injection.modules.ActivityBindingModule
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.injection.common.ExecutorModule
import com.edreams.android.workshops.kotlin.injection.common.MapperModule
import com.edreams.android.workshops.kotlin.injection.presentation.ViewModelModule
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import com.edreams.android.workshops.kotlin.util.TestApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(ActivityBindingModule::class,
    TestApplicationModule::class,
    ViewModelModule::class,
    MapperModule::class,
    ExecutorModule::class,
    AndroidSupportInjectionModule::class))
interface TestApplicationComponent {

  fun venuesRepository(): VenuesRepository

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): TestApplicationComponent
  }

  fun inject(application: TestApplication)

}