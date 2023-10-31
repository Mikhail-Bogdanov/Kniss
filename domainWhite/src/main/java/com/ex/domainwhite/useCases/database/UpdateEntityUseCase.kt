package com.ex.domainwhite.useCases.database

import com.ex.domainwhite.entities.AppEntity
import com.ex.domainwhite.repositories.database.DatabaseWhiteRepository

class UpdateEntityUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    suspend operator fun invoke(appEntity: AppEntity) =
        databaseWhiteRepository.updateEntity(appEntity)
}