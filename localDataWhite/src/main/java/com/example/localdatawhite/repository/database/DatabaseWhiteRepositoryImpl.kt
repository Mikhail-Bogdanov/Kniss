package com.example.localdatawhite.repository.database

import com.example.domainwhite.entities.AppEntity
import com.example.domainwhite.repositories.database.DatabaseWhiteRepository
import com.example.localdatawhite.database.AppDao
import com.example.localdatawhite.utils.WhiteLocalMapper.toAppDto
import com.example.localdatawhite.utils.WhiteLocalMapper.toAppEntity
import kotlinx.coroutines.flow.map

class DatabaseWhiteRepositoryImpl(
    private val appDao: AppDao
) : DatabaseWhiteRepository {

    override fun getAllEntities() = appDao.getAllEntities().map { entities ->
        entities.map { it.toAppEntity() }
    }

    override suspend fun addEntity(appEntity: AppEntity) =
        appDao.insertEntity(appEntity.toAppDto())

    override suspend fun removeEntity(appEntity: AppEntity) =
        appDao.deleteEntity(appEntity.toAppDto())

    override suspend fun updateEntity(appEntity: AppEntity) =
        appDao.updateEntity(appEntity.toAppDto())
}