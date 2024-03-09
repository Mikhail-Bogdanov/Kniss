package com.qwertyuiop.domain.useCases.theme

import com.qwertyuiop.domain.repositories.theme.ThemeRepository

class GetSavedThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getSavedTheme()
}