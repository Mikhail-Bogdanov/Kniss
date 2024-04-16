package com.evoteam.domain.useCases.theme

import com.evoteam.domain.repositories.theme.ThemeRepository

class GetSavedThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getSavedTheme()
}