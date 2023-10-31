package com.ex.domainwhite.useCases.accepting

import com.ex.domainwhite.repositories.accepting.AcceptingRepository

class SetAcceptedUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    suspend operator fun invoke() = acceptingRepository.setAccepted()
}