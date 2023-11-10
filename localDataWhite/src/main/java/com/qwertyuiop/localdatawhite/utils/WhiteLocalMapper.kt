package com.qwertyuiop.localdatawhite.utils

import com.qwertyuiop.domainwhite.entities.AppEntity
import com.qwertyuiop.localdatawhite.dto.AppDto

object WhiteLocalMapper {

    fun AppEntity.toAppDto() = AppDto(
        id = id
    )

    fun AppDto.toAppEntity() = AppEntity(
        id = id
    )

}