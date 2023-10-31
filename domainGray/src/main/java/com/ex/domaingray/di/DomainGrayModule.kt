package com.ex.domaingray.di

import com.ex.domaingray.useCases.local.GetSavedUrlUseCase
import com.ex.domaingray.useCases.local.SaveUrlUseCase
import com.ex.domaingray.useCases.remote.GetServiceResponseUseCase
import org.koin.dsl.module

object DomainGrayModule {

    val module = module {
        single {
            GetSavedUrlUseCase(localGrayRepository = get())
        }
        single {
            SaveUrlUseCase(localGrayRepository = get())
        }
        single {
            GetServiceResponseUseCase(remoteGrayRepository = get())
        }
    }
}