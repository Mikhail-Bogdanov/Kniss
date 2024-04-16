package com.evoteam.domain.useCases.knitting

import com.evoteam.domain.repositories.knitting.KnittingRepository

class GetAllKnittingsUseCase(
    private val knittingRepository: KnittingRepository
) {
    operator fun invoke() = knittingRepository.getAllKnittings()
}