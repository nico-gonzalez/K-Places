package com.edreams.android.workshops.kotlin.venues

import android.app.Application
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import com.edreams.android.workshops.kotlin.common.InstrumentationTest
import com.edreams.android.workshops.kotlin.common.injection.TestApplicationComponent
import com.edreams.android.workshops.kotlin.common.injection.TestApplicationModule
import com.edreams.android.workshops.kotlin.common.injection.modules.ActivityBindingModule
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import com.edreams.android.workshops.kotlin.injection.common.ExecutorModule
import com.edreams.android.workshops.kotlin.injection.common.MapperModule
import com.edreams.android.workshops.kotlin.injection.presentation.ViewModelModule
import com.edreams.android.workshops.kotlin.injection.scopes.PerApplication
import com.edreams.android.workshops.kotlin.test.injection.mock.repository.VenuesMockRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class VenuesActivityTest : InstrumentationTest() {

  private val venuesRepository: VenuesRepository = VenuesMockRepository()

  @PerApplication
  @Component(modules = [ActivityBindingModule::class,
    TestApplicationModule::class,
    ViewModelModule::class,
    MapperModule::class,
    ExecutorModule::class,
    AndroidSupportInjectionModule::class
  ])
  interface TestComponent : TestApplicationComponent {

    @Component.Builder
    interface Builder {
      @BindsInstance
      fun application(application: Application): Builder

      @BindsInstance
      fun venuesRepository(venuesRepository: VenuesRepository): Builder

      fun build(): TestApplicationComponent
    }
  }

  @Before
  fun setup() {
    application.appComponent = DaggerVenuesActivityTest_TestComponent.builder()
        .application(application)
        .venuesRepository(venuesRepository)
        .build()
  }

  @Test
  fun onLoadViewShowsVenues() {
    venues {
      isVisible()
      isNotLoading()
    }
  }

  @Test
  fun onVenueClickShowsDetails() {
    venues {
      isVisible()
      selectVenue()
    }
    venueDetails {
      isVisible()
    }
  }
}