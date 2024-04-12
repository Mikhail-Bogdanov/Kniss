package com.qwertyuiop.domain.di

import com.qwertyuiop.domain.useCases.theme.GetSavedThemeUseCase
import com.qwertyuiop.domain.useCases.theme.SaveThemeUseCase
import com.qwertyuiop.domain.useCases.tutorial.EndTutorialUseCase
import com.qwertyuiop.domain.useCases.tutorial.GetTutorialEndedUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DomainModule {

    val module = module {
        singleOf(::GetSavedThemeUseCase)
        singleOf(::SaveThemeUseCase)
        singleOf(::EndTutorialUseCase)
        singleOf(::GetTutorialEndedUseCase)
    }
}