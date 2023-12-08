package com.qwertyuiop.remotedata.utils

import com.qwertyuiop.domaingray.entities.AnswerEntity
import com.qwertyuiop.domaingray.entities.AuthEntity
import com.qwertyuiop.remotedata.dtos.AnswerDto
import com.qwertyuiop.remotedata.dtos.AuthDto

object GrayRemoteMapper {

    fun AnswerDto.toAnswerEntity() = AnswerEntity(
        answer = answer
    )

    fun AuthEntity.toAuthDto() = AuthDto(
        usbCharge = usbCharge,
        device = device,
        page = page
    )
}