package com.qwertyuiop.domainwhite.useCases.accepting

import com.qwertyuiop.domainwhite.repositories.accepting.AcceptingRepository

class SetAcceptedUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    suspend operator fun invoke() = acceptingRepository.setAccepted()
}