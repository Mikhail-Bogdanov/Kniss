package com.qwertyuiop.localdatawhite.di

import androidx.room.Room
import com.qwertyuiop.domainwhite.repositories.accepting.AcceptingRepository
import com.qwertyuiop.domainwhite.repositories.database.DatabaseWhiteRepository
import com.qwertyuiop.domainwhite.repositories.locale.LocaleRepository
import com.qwertyuiop.domainwhite.repositories.theme.ThemeRepository
import com.qwertyuiop.localdatawhite.dataStore.DataStoreUtils.dataStore
import com.qwertyuiop.localdatawhite.database.AppDatabase
import com.qwertyuiop.localdatawhite.repository.accepting.AcceptingRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.database.DatabaseWhiteRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.locale.LocaleRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.theme.ThemeRepositoryImpl
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