package com.qwertyuiop.domain.useCases.knitting

import com.qwertyuiop.domain.entities.Knitting
import com.qwertyuiop.domain.repositories.knitting.KnittingRepository

class RemoveKnittingUseCase(
    private val knittingRepository: KnittingRepository
) {
    suspend operator fun invoke(knitting: Knitting) = knittingRepository.removeKnitting(knitting)
}