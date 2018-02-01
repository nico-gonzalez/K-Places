package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.common.remote.FoursquareService
import com.edreams.android.workshops.kotlin.injection.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.stringBased
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.JSON
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl(): String = BuildConfig.BASE_URL

  @Provides
  fun contentType() =
      MediaType.parse("application/json")!!

  @Provides
  fun json() = JSON.nonstrict

  @Singleton
  @Provides
  fun provideRetrofit(@Named("baseUrl") baseUrl: String,
      contentType: MediaType, json: JSON): Retrofit = with(json) {
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(stringBased(contentType, ::parse, ::stringify))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
  }

  @Singleton
  @Provides
  fun provideFoursquareService(retrofit: Retrofit): FoursquareService = retrofit
      .create(FoursquareService::class.java)
}