package com.edreams.android.workshops.kotlin.data.net.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestService {
  private val BASE_URL = "https://api.foursquare.com"
  internal val API_VERSION = "v2"
  internal val CLIENT_ID = "GLZHBOBR0XIQOWEXIWBLC0ZDD1CFTPPGJBW310YG5BBLCRJX"
  internal val CLIENT_SECRET = "NXGWTTRJPTCMOZ2IPEIWJYSZVU3SZKSNJCUALHLDFIEHNVDH"
  internal val BUNDLE_VERSION = "20170801"

  val service: FoursquareService

  init {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    service = retrofit.create(FoursquareService::class.java)
  }
}
