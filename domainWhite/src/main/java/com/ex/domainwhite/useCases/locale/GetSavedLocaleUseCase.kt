package com.ex.domainwhite.useCases.locale

import com.ex.domainwhite.repositories.locale.LocaleRepository

class GetSavedLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    operator fun invoke() = localeRepository.getSavedLocale()
}