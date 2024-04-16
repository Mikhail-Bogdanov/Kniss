package com.evoteam.localData.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.entities.Loop
import java.util.UUID

@Entity(tableName = "knitting_table")
data class KnittingDto(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val width: Int,
    val height: Int,
    val loops: List<List<Loop>>,
    val currentRow: Int,
    val name: String
) {
    fun toKnittingEntity() = Knitting(
        width = width,
        height = height,
        loops = loops,
        currentRow = currentRow,
        id = id,
        name = name
    )
}
