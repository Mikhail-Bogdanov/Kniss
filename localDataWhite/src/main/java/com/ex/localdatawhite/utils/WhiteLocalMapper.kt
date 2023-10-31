package com.ex.localdatawhite.utils

import com.ex.domainwhite.entities.AppEntity
import com.ex.localdatawhite.dto.AppDto

object WhiteLocalMapper {

    fun AppEntity.toAppDto() = AppDto(
        id = id
    )

    fun AppDto.toAppEntity() = AppEntity(
        id = id
    )

}