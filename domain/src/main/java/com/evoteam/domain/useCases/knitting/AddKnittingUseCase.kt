package com.evoteam.domain.useCases.knitting

import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.repositories.knitting.KnittingRepository

class AddKnittingUseCase(
    private val knittingRepository: KnittingRepository
) {
    suspend operator fun invoke(knitting: Knitting) = knittingRepository.addKnitting(knitting)
}