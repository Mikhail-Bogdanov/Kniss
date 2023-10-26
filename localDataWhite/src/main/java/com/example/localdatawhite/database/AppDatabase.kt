package com.example.localdatawhite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localdatawhite.dto.AppDto

@Database(entities = [AppDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}