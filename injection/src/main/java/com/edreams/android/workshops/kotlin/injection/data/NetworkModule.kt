package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.common.remote.FoursquareService
import com.edreams.android.workshops.kotlin.injection.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
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
  fun provideBaseUrl(): String = BuildConfig.BASE_URL

  @Singleton
  @Provides
  fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()

  @Singleton
  @Provides
  fun provideFoursquareService(retrofit: Retrofit): FoursquareService = retrofit
      .create(FoursquareService::class.java)
}