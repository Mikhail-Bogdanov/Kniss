package com.qwertyuiop.domaingray.di

import com.qwertyuiop.domaingray.useCases.local.GetSavedUrlUseCase
import com.qwertyuiop.domaingray.useCases.local.SaveUrlUseCase
import com.qwertyuiop.domaingray.useCases.remote.GetServiceResponseUseCase
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