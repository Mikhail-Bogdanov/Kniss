package com.ex.domainwhite.repositories.database

import com.ex.domainwhite.entities.AppEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseWhiteRepository {

    fun getAllEntities(): Flow<List<AppEntity>>

    suspend fun addEntity(appEntity: AppEntity)

    suspend fun removeEntity(appEntity: AppEntity)

    suspend fun updateEntity(appEntity: AppEntity)

}