package com.example.domaingray.repositories.remote

import com.example.domaingray.entities.AnswerEntity

interface RemoteGrayRepository {

    suspend fun getServiceResponse(): AnswerEntity

}