package com.qwertyuiop.localdatagray.di

import com.qwertyuiop.domaingray.repositories.local.LocalGrayRepository
import com.qwertyuiop.localdatagray.dataStore.DataStoreUtils.dataStore
import com.qwertyuiop.localdatagray.repository.LocalGrayRepositoryImpl
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