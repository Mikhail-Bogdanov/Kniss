package com.qwertyuiop.domaingray.repositories.remote

import com.qwertyuiop.domaingray.entities.AnswerEntity

interface RemoteGrayRepository {

    suspend fun getServiceResponse(): AnswerEntity

}