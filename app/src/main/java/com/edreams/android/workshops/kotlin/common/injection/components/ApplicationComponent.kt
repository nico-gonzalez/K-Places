package com.edreams.android.workshops.kotlin.common.injection.components

import android.app.Application
import com.edreams.android.workshops.kotlin.common.KotlinWorkshopApplication
import com.edreams.android.workshops.kotlin.common.injection.modules.ActivityBindingModule
import com.edreams.android.workshops.kotlin.common.injection.modules.ApplicationModule
import com.edreams.android.workshops.kotlin.injection.data.DataComponent
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(ActivityBindingModule::class,
    ApplicationModule::class,
    AndroidSupportInjectionModule::class),
    dependencies = arrayOf(DataComponent::class))
interface ApplicationComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun dataComponent(dataComponent: DataComponent): Builder

    fun build(): ApplicationComponent
  }

  fun inject(app: KotlinWorkshopApplication)
}