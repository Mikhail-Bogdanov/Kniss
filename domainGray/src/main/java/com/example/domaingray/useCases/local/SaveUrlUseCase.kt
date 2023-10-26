package com.example.domaingray.useCases.local

import com.example.domaingray.repositories.local.LocalGrayRepository

class SaveUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    suspend operator fun invoke(url: String) = localGrayRepository.saveUrl(url)
}