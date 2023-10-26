package com.example.domaingray.useCases.local

import com.example.domaingray.repositories.local.LocalGrayRepository

class GetSavedUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    operator fun invoke() = localGrayRepository.getSavedUrl()
}