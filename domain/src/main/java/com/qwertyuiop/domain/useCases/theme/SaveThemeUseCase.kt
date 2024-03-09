package com.qwertyuiop.domain.useCases.theme

import com.qwertyuiop.domain.repositories.theme.ThemeRepository

class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(darkTheme: Boolean) = themeRepository.saveTheme(darkTheme)
}