package com.ex.localdatagray.di

import com.ex.domaingray.repositories.local.LocalGrayRepository
import com.ex.localdatagray.dataStore.DataStoreUtils.dataStore
import com.ex.localdatagray.repository.LocalGrayRepositoryImpl
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