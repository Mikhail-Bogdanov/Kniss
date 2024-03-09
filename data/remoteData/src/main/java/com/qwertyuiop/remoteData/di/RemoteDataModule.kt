package com.qwertyuiop.remoteData.di

import com.qwertyuiop.remoteData.network.ServiceApi
import com.qwertyuiop.remoteData.utils.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataModule {
    private const val SERVER_BASE_URL = ""

    val module = module {
        single {
            val authInterceptor = Interceptor { chain ->
                AuthInterceptor().intercept(chain)
            }
            val client = OkHttpClient().newBuilder()
                .addInterceptor(authInterceptor)
                .build()
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_BASE_URL)
                .client(client)
                .build()
                .create(ServiceApi::class.java)
        }
    }
}