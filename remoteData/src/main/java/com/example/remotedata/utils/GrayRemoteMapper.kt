package com.example.remotedata.utils

import com.example.domaingray.entities.AnswerEntity
import com.example.remotedata.dtos.AnswerDto

object GrayRemoteMapper {

    fun AnswerDto.toAnswerEntity() = AnswerEntity(
        answer = answer
    )
}