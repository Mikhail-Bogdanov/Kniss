package com.qwertyuiop.domaingray.di

import com.qwertyuiop.domaingray.useCases.local.GetSavedUrlUseCase
import com.qwertyuiop.domaingray.useCases.local.SaveUrlUseCase
import com.qwertyuiop.domaingray.useCases.remote.GetServiceResponseUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object DomainGrayModule {

    val module = module {
        singleOf(::GetSavedUrlUseCase)
        singleOf(::SaveUrlUseCase)
        singleOf(::GetServiceResponseUseCase)
    }
}