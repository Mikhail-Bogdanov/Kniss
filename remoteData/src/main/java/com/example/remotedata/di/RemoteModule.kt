package com.example.remotedata.di

import com.example.domaingray.repositories.remote.RemoteGrayRepository
import com.example.remotedata.network.ServiceApi
import com.example.remotedata.repository.RemoteGrayRepositoryImpl
import com.example.remotedata.utils.AuthInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteModule {

    private const val SERVER_URL = "http://188.227.32.87"

    val module = module {
        singleOf(::RemoteGrayRepositoryImpl) {
            bind<RemoteGrayRepository>()
        }
        single {
            val apiKeyInterceptor = Interceptor { chain ->
                AuthInterceptor().intercept(chain)
            }
            val client = OkHttpClient().newBuilder()
                .addInterceptor(apiKeyInterceptor)
                .build()
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .client(client)
                .build()
                .create(ServiceApi::class.java)
        }
    }
}