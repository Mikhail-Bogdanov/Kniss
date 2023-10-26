package com.example.domainwhite.useCases.theme

import com.example.domainwhite.repositories.theme.ThemeRepository

class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(darkTheme: Boolean) = themeRepository.saveTheme(darkTheme)
}