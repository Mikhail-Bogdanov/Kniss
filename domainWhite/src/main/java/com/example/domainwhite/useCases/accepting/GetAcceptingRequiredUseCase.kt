package com.example.domainwhite.useCases.accepting

import com.example.domainwhite.repositories.accepting.AcceptingRepository

class GetAcceptingRequiredUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    operator fun invoke() = acceptingRepository.getAcceptingRequired()
}