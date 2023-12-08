package com.qwertyuiop.localdatawhite.di

import androidx.room.Room
import com.qwertyuiop.localdatawhite.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

object TestLocalWhiteModule {

    val module = module {
        single(named("test")) {
            Room.inMemoryDatabaseBuilder(
                androidContext(),
                AppDatabase::class.java
            ).build()
        }
    }

}