package com.evoteam.localData.utils.mappers

import com.evoteam.domain.entities.Knitting
import com.evoteam.localData.dtos.KnittingDto

object EntityToDtoMapper {
    fun Knitting.toKnittingDto() = KnittingDto(
        width = width,
        height = height,
        loops = loops,
        currentRow = currentRow,
        name = name
    )
}