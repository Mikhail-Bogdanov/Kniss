package com.qwertyuiop.remoteData.utils

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor if needed
 */
internal class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
//            .addHeader("HEADER", BuildConfig.API_KEY)
            .build()
        return chain.proceed(builder)
    }
}