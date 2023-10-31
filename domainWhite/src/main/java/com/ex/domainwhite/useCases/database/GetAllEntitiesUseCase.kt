package com.ex.domainwhite.useCases.database

import com.ex.domainwhite.repositories.database.DatabaseWhiteRepository

class GetAllEntitiesUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    operator fun invoke() = databaseWhiteRepository.getAllEntities()
}