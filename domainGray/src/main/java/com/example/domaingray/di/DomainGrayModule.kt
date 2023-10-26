package com.example.domaingray.di

import com.example.domaingray.useCases.local.GetSavedUrlUseCase
import com.example.domaingray.useCases.local.SaveUrlUseCase
import com.example.domaingray.useCases.remote.GetServiceResponseUseCase
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