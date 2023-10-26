package com.example.domainwhite.repositories.accepting

import kotlinx.coroutines.flow.Flow

interface AcceptingRepository {

    fun getAcceptingRequired(): Flow<Boolean>

    suspend fun setAccepted()

}