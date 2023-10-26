package com.example.domainwhite.useCases.locale

import com.example.domainwhite.repositories.locale.LocaleRepository

class GetSavedLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    operator fun invoke() = localeRepository.getSavedLocale()
}