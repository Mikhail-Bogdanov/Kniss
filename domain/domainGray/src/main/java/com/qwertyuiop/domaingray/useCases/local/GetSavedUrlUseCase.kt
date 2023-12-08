package com.qwertyuiop.domaingray.useCases.local

import com.qwertyuiop.domaingray.repositories.local.LocalGrayRepository

class GetSavedUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    operator fun invoke() = localGrayRepository.getSavedUrl()
}