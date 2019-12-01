package com.edreams.android.workshops.kotlin.injection.data

import com.edreams.android.workshops.kotlin.data.common.http.interceptors.ApiKeyInterceptor
import com.edreams.android.workshops.kotlin.data.common.remote.FoursquareService
import com.edreams.android.workshops.kotlin.injection.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Named("baseUrl")
  fun provideBaseUrl(): String = BuildConfig.BASE_URL

  @Provides
  fun provideContentType(): MediaType = "application/json".toMediaType()

  @Provides
  @Named("loggingInterceptor")
  fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG)
      HttpLoggingInterceptor.Level.BODY
    else
      HttpLoggingInterceptor.Level.NONE
  }

  @Provides
  @Named("apiKeyInterceptor")
  fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

  @Provides
  fun provideOkHttpClient(
    @Named("loggingInterceptor") loggingInterceptor: Interceptor,
    @Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor
  ): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(apiKeyInterceptor)
    .build()

  @UnstableDefault
  @Provides
  fun provideJson(): Json = Json.nonstrict

  @Singleton
  @Provides
  fun provideRetrofit(
    client: OkHttpClient,
    @Named("baseUrl") baseUrl: String,
    contentType: MediaType,
    json: Json
  ): Retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl(baseUrl)
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()

  @Singleton
  @Provides
  fun provideFoursquareService(retrofit: Retrofit): FoursquareService = retrofit
    .create(FoursquareService::class.java)
}