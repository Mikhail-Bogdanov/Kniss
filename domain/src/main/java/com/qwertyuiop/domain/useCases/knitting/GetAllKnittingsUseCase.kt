package com.qwertyuiop.domain.useCases.knitting

import com.qwertyuiop.domain.repositories.knitting.KnittingRepository

class GetAllKnittingsUseCase(
    private val knittingRepository: KnittingRepository
) {
    operator fun invoke() = knittingRepository.getAllKnittings()
}