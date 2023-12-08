package com.qwertyuiop.domainwhite.useCases.theme

import com.qwertyuiop.domainwhite.repositories.theme.ThemeRepository

class GetSavedThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    operator fun invoke() = themeRepository.getSavedTheme()
}