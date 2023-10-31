package com.ex.domainwhite.di

import com.ex.domainwhite.useCases.accepting.GetAcceptingRequiredUseCase
import com.ex.domainwhite.useCases.accepting.SetAcceptedUseCase
import com.ex.domainwhite.useCases.database.AddEntityUseCase
import com.ex.domainwhite.useCases.database.GetAllEntitiesUseCase
import com.ex.domainwhite.useCases.database.RemoveEntityUseCase
import com.ex.domainwhite.useCases.database.UpdateEntityUseCase
import com.ex.domainwhite.useCases.locale.GetSavedLocaleUseCase
import com.ex.domainwhite.useCases.locale.SaveLocaleUseCase
import com.ex.domainwhite.useCases.theme.GetSavedThemeUseCase
import com.ex.domainwhite.useCases.theme.SaveThemeUseCase
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