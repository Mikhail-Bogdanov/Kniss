package com.ex.domaingray.repositories.remote

import com.ex.domaingray.entities.AnswerEntity

interface RemoteGrayRepository {

    suspend fun getServiceResponse(): AnswerEntity

}