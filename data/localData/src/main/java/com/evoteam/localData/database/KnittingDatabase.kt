package com.evoteam.localData.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evoteam.localData.database.typeConverters.KnittingDatabaseTypeConverter
import com.evoteam.localData.dtos.KnittingDto

@Database(entities = [KnittingDto::class], version = 1)
@TypeConverters(KnittingDatabaseTypeConverter::class)
abstract class KnittingDatabase : RoomDatabase() {
    abstract val knittingDao: KnittingDao
}