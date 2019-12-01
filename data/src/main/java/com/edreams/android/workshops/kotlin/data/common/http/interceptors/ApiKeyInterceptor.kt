package com.edreams.android.workshops.kotlin.data.common.http.interceptors

import com.edreams.android.workshops.kotlin.data.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

  override fun intercept(chain: Chain): Response {
    val original: Request = chain.request()
    val originalHttpUrl: HttpUrl = original.url

    val url = originalHttpUrl.newBuilder()
      .addQueryParameter("v", "20191231")
      .addQueryParameter("client_id", BuildConfig.FS_CLIENT_ID)
      .addQueryParameter("client_secret", BuildConfig.FS_CLIENT_SECRET)
      .build()

    val request = original.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}
