package com.edreams.android.workshops.kotlin.injection.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.edreams.android.workshops.kotlin.presentation.venues.VenuesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(VenuesViewModel::class)
  abstract fun bindVenuesViewModel(venuesViewModel: VenuesViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}