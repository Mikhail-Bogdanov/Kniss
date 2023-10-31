package com.ex.remotedata.utils

import com.ex.remotedata.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
            .addHeader("to", BuildConfig.API_KEY)
            .build()
        return chain.proceed(builder)
    }
}