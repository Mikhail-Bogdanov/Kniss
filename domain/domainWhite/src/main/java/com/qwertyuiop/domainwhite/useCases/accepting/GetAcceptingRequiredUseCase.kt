package com.qwertyuiop.domainwhite.useCases.accepting

import com.qwertyuiop.domainwhite.repositories.accepting.AcceptingRepository

class GetAcceptingRequiredUseCase(
    private val acceptingRepository: AcceptingRepository
) {
    operator fun invoke() = acceptingRepository.getAcceptingRequired()
}