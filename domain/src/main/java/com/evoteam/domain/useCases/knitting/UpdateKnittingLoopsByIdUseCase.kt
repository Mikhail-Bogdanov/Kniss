package com.evoteam.domain.useCases.knitting

import com.evoteam.domain.entities.Loop
import com.evoteam.domain.repositories.knitting.KnittingRepository

class UpdateKnittingLoopsByIdUseCase(
    private val knittingRepository: KnittingRepository
) {
    suspend operator fun invoke(
        loops: List<List<Loop>>,
        id: String
    ) = knittingRepository.updateLoopsById(loops, id)
}