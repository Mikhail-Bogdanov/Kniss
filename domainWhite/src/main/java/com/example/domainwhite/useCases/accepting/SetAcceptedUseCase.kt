package com.example.domainwhite.useCases.accepting

import com.example.domainwhite.repositories.accepting.AcceptingRepository

class SetAcceptedUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    suspend operator fun invoke() = acceptingRepository.setAccepted()
}