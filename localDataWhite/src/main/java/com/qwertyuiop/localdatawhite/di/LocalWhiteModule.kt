package com.qwertyuiop.localdatawhite.di

import androidx.room.Room
import com.qwertyuiop.domainwhite.repositories.accepting.AcceptingRepository
import com.qwertyuiop.domainwhite.repositories.database.DatabaseWhiteRepository
import com.qwertyuiop.domainwhite.repositories.locale.LocaleRepository
import com.qwertyuiop.domainwhite.repositories.theme.ThemeRepository
import com.qwertyuiop.localdatawhite.database.AppDatabase
import com.qwertyuiop.localdatawhite.repository.accepting.AcceptingRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.database.DatabaseWhiteRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.locale.LocaleRepositoryImpl
import com.qwertyuiop.localdatawhite.repository.theme.ThemeRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

object LocalWhiteModule {

    val module = module {
        singleOf(::DatabaseWhiteRepositoryImpl) {
            bind<DatabaseWhiteRepository>()
        }
        singleOf(::LocaleRepositoryImpl) {
            bind<LocaleRepository>()
        }
        singleOf(::AcceptingRepositoryImpl) {
            bind<AcceptingRepository>()
        }
        singleOf(::ThemeRepositoryImpl) {
            bind<ThemeRepository>()
        }

        single(named("main")) {
            Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                APP_DATABASE_NAME
            ).build()
        }

        single { get<AppDatabase>(named("main")).appDao }
    }

    private const val APP_DATABASE_NAME = "app_database"
}