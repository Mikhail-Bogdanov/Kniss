package com.ex.domainwhite.useCases.theme

import com.ex.domainwhite.repositories.theme.ThemeRepository

class GetSavedThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getSavedTheme()
}