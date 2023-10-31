package com.ex.domaingray.useCases.local

import com.ex.domaingray.repositories.local.LocalGrayRepository

class GetSavedUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    operator fun invoke() = localGrayRepository.getSavedUrl()
}