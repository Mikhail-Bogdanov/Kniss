package com.ex.domainwhite.useCases.locale

import com.ex.domainwhite.repositories.locale.LocaleRepository

class SaveLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    suspend operator fun invoke(locale: String) = localeRepository.saveLocale(locale)
}