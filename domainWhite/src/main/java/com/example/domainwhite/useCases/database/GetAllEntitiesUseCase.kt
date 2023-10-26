package com.example.domainwhite.useCases.database

import com.example.domainwhite.repositories.database.DatabaseWhiteRepository

class GetAllEntitiesUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    operator fun invoke() = databaseWhiteRepository.getAllEntities()
}