package com.qwertyuiop.domainwhite.useCases.locale

import com.qwertyuiop.domainwhite.repositories.locale.LocaleRepository

class GetSavedLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    operator fun invoke() = localeRepository.getSavedLocale()
}