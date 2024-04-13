package com.qwertyuiop.localData.di

import androidx.room.Room
import com.qwertyuiop.domain.repositories.knitting.KnittingRepository
import com.qwertyuiop.domain.repositories.theme.ThemeRepository
import com.qwertyuiop.domain.repositories.tutorial.TutorialRepository
import com.qwertyuiop.localData.dataStore.DataStoreUtils.dataStore
import com.qwertyuiop.localData.database.KnittingDatabase
import com.qwertyuiop.localData.repository.knitting.KnittingRepositoryImpl
import com.qwertyuiop.localData.repository.theme.ThemeRepositoryImpl
import com.qwertyuiop.localData.repository.tutorial.TutorialRepositoryImpl
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