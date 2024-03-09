package com.qwertyuiop.localData.di

import androidx.room.Room
import com.qwertyuiop.domain.repositories.theme.ThemeRepository
import com.qwertyuiop.localData.dataStore.DataStoreUtils.dataStore
import com.qwertyuiop.localData.database.AppDatabase
import com.qwertyuiop.localData.repository.theme.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object LocalDataModule {

    val module = module {
        singleOf(::ThemeRepositoryImpl) {
            bind<ThemeRepository>()
        }

        single { androidContext().dataStore }

        single {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                APP_DATABASE_NAME
            ).build().appDao
        }
    }

    private const val APP_DATABASE_NAME = "app_database"
}