package com.ex.domaingray.useCases.remote

import com.ex.domaingray.repositories.remote.RemoteGrayRepository

class GetServiceResponseUseCase(
    private val remoteGrayRepository: RemoteGrayRepository
) {
    suspend operator fun invoke() = remoteGrayRepository.getServiceResponse()
}