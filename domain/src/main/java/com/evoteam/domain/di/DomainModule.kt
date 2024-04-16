package com.evoteam.domain.di

import com.evoteam.domain.useCases.knitting.AddKnittingUseCase
import com.evoteam.domain.useCases.knitting.GetAllKnittingsUseCase
import com.evoteam.domain.useCases.knitting.RemoveKnittingUseCase
import com.evoteam.domain.useCases.knitting.UpdateKnittingLoopsByIdUseCase
import com.evoteam.domain.useCases.knitting.UpdateKnittingRowByIdUseCase
import com.evoteam.domain.useCases.theme.GetSavedThemeUseCase
import com.evoteam.domain.useCases.theme.SaveThemeUseCase
import com.evoteam.domain.useCases.tutorial.EndTutorialUseCase
import com.evoteam.domain.useCases.tutorial.GetTutorialEndedUseCase
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