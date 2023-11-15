package com.qwertyuiop.domainwhite.di

import com.qwertyuiop.domainwhite.useCases.accepting.GetAcceptingRequiredUseCase
import com.qwertyuiop.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.qwertyuiop.domainwhite.useCases.database.AddEntityUseCase
import com.qwertyuiop.domainwhite.useCases.database.GetAllEntitiesUseCase
import com.qwertyuiop.domainwhite.useCases.database.RemoveEntityUseCase
import com.qwertyuiop.domainwhite.useCases.database.UpdateEntityUseCase
import com.qwertyuiop.domainwhite.useCases.locale.GetSavedLocaleUseCase
import com.qwertyuiop.domainwhite.useCases.locale.SaveLocaleUseCase
import com.qwertyuiop.domainwhite.useCases.theme.GetSavedThemeUseCase
import com.qwertyuiop.domainwhite.useCases.theme.SaveThemeUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DomainWhiteModule {

    val module = module {
        singleOf(::AddEntityUseCase)
        singleOf(::GetAllEntitiesUseCase)
        singleOf(::RemoveEntityUseCase)
        singleOf(::UpdateEntityUseCase)
        singleOf(::GetSavedLocaleUseCase)
        singleOf(::SaveLocaleUseCase)
        singleOf(::GetSavedThemeUseCase)
        singleOf(::SaveThemeUseCase)
        singleOf(::GetAcceptingRequiredUseCase)
        singleOf(::SetAcceptedUseCase)
    }
}