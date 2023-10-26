package com.example.localdatawhite.di

import androidx.room.Room
import com.example.domainwhite.repositories.accepting.AcceptingRepository
import com.example.domainwhite.repositories.database.DatabaseWhiteRepository
import com.example.domainwhite.repositories.locale.LocaleRepository
import com.example.domainwhite.repositories.theme.ThemeRepository
import com.example.localdatawhite.dataStore.DataStoreUtils.dataStore
import com.example.localdatawhite.database.AppDatabase
import com.example.localdatawhite.repository.accepting.AcceptingRepositoryImpl
import com.example.localdatawhite.repository.database.DatabaseWhiteRepositoryImpl
import com.example.localdatawhite.repository.locale.LocaleRepositoryImpl
import com.example.localdatawhite.repository.theme.ThemeRepositoryImpl
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