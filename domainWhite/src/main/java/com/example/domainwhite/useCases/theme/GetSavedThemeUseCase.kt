package com.example.domainwhite.useCases.theme

import com.example.domainwhite.repositories.theme.ThemeRepository

class GetSavedThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getSavedTheme()
}