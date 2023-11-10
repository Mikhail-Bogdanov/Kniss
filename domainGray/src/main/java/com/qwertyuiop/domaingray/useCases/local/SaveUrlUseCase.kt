package com.qwertyuiop.domaingray.useCases.local

import com.qwertyuiop.domaingray.repositories.local.LocalGrayRepository

class SaveUrlUseCase(
    private val localGrayRepository: LocalGrayRepository
) {
    suspend operator fun invoke(url: String) = localGrayRepository.saveUrl(url)
}