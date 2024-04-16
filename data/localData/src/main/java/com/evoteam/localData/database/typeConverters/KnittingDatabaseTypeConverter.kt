package com.evoteam.localData.database.typeConverters

import androidx.room.TypeConverter
import com.evoteam.domain.entities.Loop
import com.evoteam.localData.utils.DataJson
import kotlinx.serialization.encodeToString

class KnittingDatabaseTypeConverter {
    @TypeConverter
    fun List<List<Loop>>.convertToString() = DataJson.encodeToString(this)

    @TypeConverter
    fun String.toLoopGrid() = DataJson.decodeFromString<List<List<Loop>>>(this)
}