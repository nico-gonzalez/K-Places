package com.edreams.android.workshops.kotlin.common.injection.modules

import android.content.Context
import android.support.test.espresso.idling.CountingIdlingResource
import com.edreams.android.workshops.kotlin.common.KotlinWorkshopApplication
import com.edreams.android.workshops.kotlin.common.injection.scopes.PerApplication
import com.edreams.android.workshops.kotlin.data.mapper.VenueMapper
import com.edreams.android.workshops.kotlin.data.net.controller.ExploreVenuesNetController
import com.edreams.android.workshops.kotlin.data.net.service.FoursquareService
import com.edreams.android.workshops.kotlin.data.repository.VenuesDataRepository
import com.edreams.android.workshops.kotlin.domain.repositories.VenuesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApplicationModule {

  @PerApplication
  @Provides
  fun provideContext(application: KotlinWorkshopApplication): Context = application

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl(): String = "https://api.foursquare.com"

  @PerApplication
  @Provides
  fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  @PerApplication
  @Provides
  fun provideFoursquareService(retrofit: Retrofit): FoursquareService = retrofit
      .create(FoursquareService::class.java)

  @PerApplication
  @Provides
  fun provideIdlingResource(): CountingIdlingResource = CountingIdlingResource(
      "CustomIdlingResource")

  @PerApplication
  @Provides
  fun provideVenuesRepository(
      mapper: VenueMapper,
      exploreVenuesController: ExploreVenuesNetController): VenuesRepository = VenuesDataRepository(
      mapper, exploreVenuesController)
}