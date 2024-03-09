package com.qwertyuiop.domain.di

import com.qwertyuiop.domain.useCases.theme.GetSavedThemeUseCase
import com.qwertyuiop.domain.useCases.theme.SaveThemeUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DomainModule {

    val module = module {
        singleOf(::GetSavedThemeUseCase)
        singleOf(::SaveThemeUseCase)
    }
}