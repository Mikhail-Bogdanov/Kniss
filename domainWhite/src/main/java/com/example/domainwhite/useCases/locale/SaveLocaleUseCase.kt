package com.example.domainwhite.useCases.locale

import com.example.domainwhite.repositories.locale.LocaleRepository

class SaveLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    suspend operator fun invoke(locale: String) = localeRepository.saveLocale(locale)
}