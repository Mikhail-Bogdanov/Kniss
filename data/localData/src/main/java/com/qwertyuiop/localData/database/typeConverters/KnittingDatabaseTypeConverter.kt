package com.qwertyuiop.localData.database.typeConverters

import androidx.room.TypeConverter
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.localData.utils.DataJson
import kotlinx.serialization.encodeToString

class KnittingDatabaseTypeConverter {
    @TypeConverter
    fun List<List<Loop>>.convertToString() = DataJson.encodeToString(this)

    @TypeConverter
    fun String.toLoopGrid() = DataJson.decodeFromString<List<List<Loop>>>(this)
}