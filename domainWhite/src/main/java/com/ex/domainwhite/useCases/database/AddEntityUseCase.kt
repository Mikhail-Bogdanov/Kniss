package com.ex.domainwhite.useCases.database

import com.ex.domainwhite.entities.AppEntity
import com.ex.domainwhite.repositories.database.DatabaseWhiteRepository

class AddEntityUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    suspend operator fun invoke(appEntity: AppEntity) =
        databaseWhiteRepository.addEntity(appEntity)
}