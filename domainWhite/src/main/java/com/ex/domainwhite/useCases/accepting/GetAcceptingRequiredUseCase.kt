package com.ex.domainwhite.useCases.accepting

import com.ex.domainwhite.repositories.accepting.AcceptingRepository

class GetAcceptingRequiredUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    operator fun invoke() = acceptingRepository.getAcceptingRequired()
}