package com.evoteam.domain.useCases.theme

import com.evoteam.domain.repositories.theme.ThemeRepository

class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(darkTheme: Boolean) = themeRepository.saveTheme(darkTheme)
}