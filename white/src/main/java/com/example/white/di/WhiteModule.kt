package com.example.white.di

import com.example.white.ui.composables.host.mvi.HostViewModel
import com.example.white.ui.composables.loading.mvi.LoadingViewModel
import com.example.white.ui.composables.main.mvi.MainViewModel
import com.example.white.ui.composables.settings.mvi.SettingsViewModel
import com.example.white.ui.composables.start.mvi.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object WhiteModule {
    val module = module {
        viewModel {
            HostViewModel(
                getSavedThemeUseCase = get(),
                getSavedLocaleUseCase = get(),
                getAcceptingRequiredUseCase = get(),
                setAcceptedUseCase = get()
            )
        }
        viewModel {
            SettingsViewModel(
                saveLocaleUseCase = get(),
                saveThemeUseCase = get()
            )
        }
        viewModel {
            LoadingViewModel(
                getServiceResponseUseCase = get(),
                getSavedUrlUseCase = get(),
                saveUrlUseCase = get()
            )
        }
        viewModel {
            StartViewModel()
        }
        viewModel {
            MainViewModel()
        }
    }
}