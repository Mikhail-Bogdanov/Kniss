package com.ex.localdatawhite.repository.database

import com.ex.domainwhite.entities.AppEntity
import com.ex.domainwhite.repositories.database.DatabaseWhiteRepository
import com.ex.localdatawhite.database.AppDao
import com.ex.localdatawhite.utils.WhiteLocalMapper.toAppDto
import com.ex.localdatawhite.utils.WhiteLocalMapper.toAppEntity
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