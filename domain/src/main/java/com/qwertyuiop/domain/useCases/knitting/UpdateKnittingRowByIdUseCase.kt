package com.qwertyuiop.domain.useCases.knitting

import com.qwertyuiop.domain.repositories.knitting.KnittingRepository

class UpdateKnittingRowByIdUseCase(
    private val knittingRepository: KnittingRepository
) {
    suspend operator fun invoke(
        currentRow: Int,
        id: String
    ) = knittingRepository.updateCurrentRowById(currentRow, id)
}