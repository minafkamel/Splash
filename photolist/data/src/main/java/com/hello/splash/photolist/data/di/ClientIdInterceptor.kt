package com.hello.splash.photolist.data.di

import com.hello.splash.photolist.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ClientIdInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Client-ID ${BuildConfig.CLIENT_ID}")
            .build()

        return chain.proceed(newRequest)
    }
}
