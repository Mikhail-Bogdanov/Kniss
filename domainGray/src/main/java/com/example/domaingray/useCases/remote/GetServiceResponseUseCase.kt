package com.example.domaingray.useCases.remote

import com.example.domaingray.repositories.remote.RemoteGrayRepository

class GetServiceResponseUseCase(
    private val remoteGrayRepository: RemoteGrayRepository
) {
    suspend operator fun invoke() = remoteGrayRepository.getServiceResponse()
}