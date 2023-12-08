package com.qwertyuiop.domainwhite.useCases.database

import com.qwertyuiop.domainwhite.entities.AppEntity
import com.qwertyuiop.domainwhite.repositories.database.DatabaseWhiteRepository

class RemoveEntityUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    suspend operator fun invoke(appEntity: AppEntity) =
        databaseWhiteRepository.removeEntity(appEntity)
}