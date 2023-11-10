package com.qwertyuiop.domainwhite.useCases.locale

import com.qwertyuiop.domainwhite.repositories.locale.LocaleRepository

class SaveLocaleUseCase(
    private val localeRepository: LocaleRepository
) {
    suspend operator fun invoke(locale: String) = localeRepository.saveLocale(locale)
}