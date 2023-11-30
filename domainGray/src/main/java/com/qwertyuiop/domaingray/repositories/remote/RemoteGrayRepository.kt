package com.qwertyuiop.domaingray.repositories.remote

import com.qwertyuiop.domaingray.entities.AnswerEntity
import com.qwertyuiop.domaingray.entities.AuthEntity

interface RemoteGrayRepository {

    suspend fun getServiceResponse(authEntity: AuthEntity): AnswerEntity

}