package com.qwertyuiop.domainwhite.useCases.database

import com.qwertyuiop.domainwhite.repositories.database.DatabaseWhiteRepository

class GetAllEntitiesUseCase(
    private val databaseWhiteRepository: DatabaseWhiteRepository
) {
    operator fun invoke() = databaseWhiteRepository.getAllEntities()
}