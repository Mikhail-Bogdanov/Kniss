package com.example.domainwhite.useCases.database

import com.example.domainwhite.entities.AppEntity
import com.example.domainwhite.repositories.database.DatabaseWhiteRepository

class AddEntityUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    suspend operator fun invoke(appEntity: AppEntity) =
        databaseWhiteRepository.addEntity(appEntity)
}