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