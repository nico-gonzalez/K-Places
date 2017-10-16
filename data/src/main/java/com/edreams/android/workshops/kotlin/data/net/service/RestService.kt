package com.edreams.android.workshops.kotlin.data.net.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestService {
  private val BASE_URL = "https://api.foursquare.com"

  val service: FoursquareService

  init {
    service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoursquareService::class.java)
  }
}
