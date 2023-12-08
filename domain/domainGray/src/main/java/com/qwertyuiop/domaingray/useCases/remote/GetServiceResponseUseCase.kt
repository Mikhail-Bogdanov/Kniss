package com.qwertyuiop.domaingray.useCases.remote

import com.qwertyuiop.domaingray.entities.AuthEntity
import com.qwertyuiop.domaingray.repositories.remote.RemoteGrayRepository

class GetServiceResponseUseCase(
    private val remoteGrayRepository: RemoteGrayRepository
) {
    suspend operator fun invoke(authEntity: AuthEntity) =
        remoteGrayRepository.getServiceResponse(authEntity)
}