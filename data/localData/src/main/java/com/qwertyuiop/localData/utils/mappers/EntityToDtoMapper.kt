package com.qwertyuiop.localData.utils.mappers

import com.qwertyuiop.domain.entities.Knitting
import com.qwertyuiop.localData.dtos.KnittingDto

object EntityToDtoMapper {
    fun Knitting.toKnittingDto() = KnittingDto(
        width = width,
        height = height,
        loops = loops,
        currentRow = currentRow,
        name = name
    )
}