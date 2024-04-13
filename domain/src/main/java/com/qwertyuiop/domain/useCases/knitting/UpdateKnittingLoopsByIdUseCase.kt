package com.qwertyuiop.domain.useCases.knitting

import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.domain.repositories.knitting.KnittingRepository

class UpdateKnittingLoopsByIdUseCase(
    private val knittingRepository: KnittingRepository
) {
    suspend operator fun invoke(
        loops: List<List<Loop>>,
        id: String
    ) = knittingRepository.updateLoopsById(loops, id)
}