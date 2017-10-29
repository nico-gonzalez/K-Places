package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.net.service.FoursquareService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl(): String = "https://api.foursquare.com"

  @Singleton
  @Provides
  fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  @Singleton
  @Provides
  fun provideFoursquareService(retrofit: Retrofit): FoursquareService = retrofit
      .create(FoursquareService::class.java)
}