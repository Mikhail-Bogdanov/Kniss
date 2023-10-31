package com.ex.localdatawhite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ex.localdatawhite.dto.AppDto

@Database(entities = [AppDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}