package com.qwertyuiop.remotedata.utils

import com.qwertyuiop.domaingray.entities.AnswerEntity
import com.qwertyuiop.remotedata.dtos.AnswerDto

object GrayRemoteMapper {

    fun AnswerDto.toAnswerEntity() = AnswerEntity(
        answer = answer
    )
}