package com.ex.localdatawhite.di

import androidx.room.Room
import com.ex.domainwhite.repositories.accepting.AcceptingRepository
import com.ex.domainwhite.repositories.database.DatabaseWhiteRepository
import com.ex.domainwhite.repositories.locale.LocaleRepository
import com.ex.domainwhite.repositories.theme.ThemeRepository
import com.ex.localdatawhite.dataStore.DataStoreUtils.dataStore
import com.ex.localdatawhite.database.AppDatabase
import com.ex.localdatawhite.repository.accepting.AcceptingRepositoryImpl
import com.ex.localdatawhite.repository.database.DatabaseWhiteRepositoryImpl
import com.ex.localdatawhite.repository.locale.LocaleRepositoryImpl
import com.ex.localdatawhite.repository.theme.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object LocalWhiteModule {

    val module = module {
        single<DatabaseWhiteRepository> {
            DatabaseWhiteRepositoryImpl(appDao = get())
        }
        single<LocaleRepository> {
            LocaleRepositoryImpl(dataStore = get())
        }
        single<AcceptingRepository> {
            AcceptingRepositoryImpl(dataStore = get())
        }
        single<ThemeRepository> {
            ThemeRepositoryImpl(dataStore = get())
        }
        single {
            androidContext().dataStore
        }
        single {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                APP_DATABASE_NAME
            ).build().getAppDao()
        }
    }

    private const val APP_DATABASE_NAME = "app_database"
}