package com.qwertyuiop.domainwhite.useCases.theme

import com.qwertyuiop.domainwhite.repositories.theme.ThemeRepository

class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(darkTheme: Boolean) = themeRepository.saveTheme(darkTheme)
}