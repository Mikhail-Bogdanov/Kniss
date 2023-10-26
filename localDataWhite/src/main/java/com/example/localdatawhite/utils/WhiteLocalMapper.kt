package com.example.localdatawhite.utils

import com.example.domainwhite.entities.AppEntity
import com.example.localdatawhite.dto.AppDto

object WhiteLocalMapper {

    fun AppEntity.toAppDto() = AppDto(
        id = id
    )

    fun AppDto.toAppEntity() = AppEntity(
        id = id
    )

}