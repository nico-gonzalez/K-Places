package com.edreams.android.workshops.kotlin.common.injection.modules

import com.edreams.android.workshops.kotlin.injection.scopes.PerActivity
import com.edreams.android.workshops.kotlin.venues.VenuesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @PerActivity
  @ContributesAndroidInjector(modules = arrayOf(VenuesActivityModule::class))
  abstract fun bindVenuesActivity(): VenuesActivity
}