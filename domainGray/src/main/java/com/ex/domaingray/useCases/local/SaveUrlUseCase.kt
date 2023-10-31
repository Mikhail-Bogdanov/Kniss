package com.ex.domaingray.useCases.local

import com.ex.domaingray.repositories.local.LocalGrayRepository

class SaveUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    suspend operator fun invoke(url: String) = localGrayRepository.saveUrl(url)
}