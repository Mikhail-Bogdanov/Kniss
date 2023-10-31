package com.ex.remotedata.utils

import com.ex.domaingray.entities.AnswerEntity
import com.ex.remotedata.dtos.AnswerDto

object GrayRemoteMapper {

    fun AnswerDto.toAnswerEntity() = AnswerEntity(
        answer = answer
    )
}