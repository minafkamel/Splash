package com.helloclue.androidassignment.photolist.data.di

import okhttp3.Interceptor
import okhttp3.Response

object ClientIdInterceptor : Interceptor {

    // Ideally should not be exposed but in the local properties that are not pushed to github for e.g
    private const val id = "qYxi_DjjjMBSh-8Tzq-SE3Nt7aIOjBcAk0bxIva6vjE"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Client-ID $id")
            .build()

        return chain.proceed(newRequest)
    }
}
