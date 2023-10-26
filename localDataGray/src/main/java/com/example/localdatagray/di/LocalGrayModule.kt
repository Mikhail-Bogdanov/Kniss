package com.example.localdatagray.di

import com.example.domaingray.repositories.local.LocalGrayRepository
import com.example.localdatagray.dataStore.DataStoreUtils.dataStore
import com.example.localdatagray.repository.LocalGrayRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LocalGrayModule {

    val module = module {
        singleOf(::LocalGrayRepositoryImpl) {
            bind<LocalGrayRepository>()
        }
        single {
            androidContext().dataStore
        }
    }
}