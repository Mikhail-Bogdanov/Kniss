package com.ex.domainwhite.useCases.theme

import com.ex.domainwhite.repositories.theme.ThemeRepository

class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(darkTheme: Boolean) = themeRepository.saveTheme(darkTheme)
}