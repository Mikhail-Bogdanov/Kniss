package com.example.domainwhite.di

import com.example.domainwhite.useCases.accepting.GetAcceptingRequiredUseCase
import com.example.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.example.domainwhite.useCases.database.AddEntityUseCase
import com.example.domainwhite.useCases.database.GetAllEntitiesUseCase
import com.example.domainwhite.useCases.database.RemoveEntityUseCase
import com.example.domainwhite.useCases.database.UpdateEntityUseCase
import com.example.domainwhite.useCases.locale.GetSavedLocaleUseCase
import com.example.domainwhite.useCases.locale.SaveLocaleUseCase
import com.example.domainwhite.useCases.theme.GetSavedThemeUseCase
import com.example.domainwhite.useCases.theme.SaveThemeUseCase
import org.koin.dsl.module

object DomainWhiteModule {

    val module = module {
        single {
            AddEntityUseCase(databaseWhiteRepository = get())
        }
        single {
            GetAllEntitiesUseCase(databaseWhiteRepository = get())
        }
        single {
            RemoveEntityUseCase(databaseWhiteRepository = get())
        }
        single {
            UpdateEntityUseCase(databaseWhiteRepository = get())
        }
        single {
            GetSavedLocaleUseCase(localeRepository = get())
        }
        single {
            SaveLocaleUseCase(localeRepository = get())
        }
        single {
            GetSavedThemeUseCase(themeRepository = get())
        }
        single {
            SaveThemeUseCase(themeRepository = get())
        }
        single {
            GetAcceptingRequiredUseCase(acceptingRepository = get())
        }
        single {
            SetAcceptedUseCase(acceptingRepository = get())
        }
    }
}