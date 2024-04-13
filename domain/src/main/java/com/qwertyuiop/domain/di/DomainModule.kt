package com.qwertyuiop.domain.di

import com.qwertyuiop.domain.useCases.knitting.AddKnittingUseCase
import com.qwertyuiop.domain.useCases.knitting.GetAllKnittingsUseCase
import com.qwertyuiop.domain.useCases.knitting.RemoveKnittingUseCase
import com.qwertyuiop.domain.useCases.knitting.UpdateKnittingLoopsByIdUseCase
import com.qwertyuiop.domain.useCases.knitting.UpdateKnittingRowByIdUseCase
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
        singleOf(::GetAllKnittingsUseCase)
        singleOf(::RemoveKnittingUseCase)
        singleOf(::AddKnittingUseCase)
        singleOf(::UpdateKnittingRowByIdUseCase)
        singleOf(::UpdateKnittingLoopsByIdUseCase)
    }
}