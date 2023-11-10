package com.qwertyuiop.localdatawhite.repository.database

import com.qwertyuiop.domainwhite.entities.AppEntity
import com.qwertyuiop.domainwhite.repositories.database.DatabaseWhiteRepository
import com.qwertyuiop.localdatawhite.database.AppDao
import com.qwertyuiop.localdatawhite.utils.WhiteLocalMapper.toAppDto
import com.qwertyuiop.localdatawhite.utils.WhiteLocalMapper.toAppEntity
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