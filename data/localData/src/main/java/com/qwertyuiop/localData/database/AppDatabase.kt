package com.qwertyuiop.localData.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qwertyuiop.localData.dtos.AppDto

@Database(entities = [AppDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val appDao: AppDao
}