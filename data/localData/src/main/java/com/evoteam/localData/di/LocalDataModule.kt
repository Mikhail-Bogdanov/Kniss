package com.evoteam.localData.di

import androidx.room.Room
import com.evoteam.domain.repositories.knitting.KnittingRepository
import com.evoteam.domain.repositories.theme.ThemeRepository
import com.evoteam.domain.repositories.tutorial.TutorialRepository
import com.evoteam.localData.dataStore.DataStoreUtils.dataStore
import com.evoteam.localData.database.KnittingDatabase
import com.evoteam.localData.repository.knitting.KnittingRepositoryImpl
import com.evoteam.localData.repository.theme.ThemeRepositoryImpl
import com.evoteam.localData.repository.tutorial.TutorialRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object LocalDataModule {

    val module = module {
        singleOf(::ThemeRepositoryImpl) bind ThemeRepository::class
        singleOf(::TutorialRepositoryImpl) bind TutorialRepository::class
        singleOf(::KnittingRepositoryImpl) bind KnittingRepository::class

        single { androidContext().dataStore }

        single {
            Room.databaseBuilder(
                androidContext(),
                KnittingDatabase::class.java,
                APP_DATABASE_NAME
            ).build().knittingDao
        }
    }

    private const val APP_DATABASE_NAME = "knitting_database"
}