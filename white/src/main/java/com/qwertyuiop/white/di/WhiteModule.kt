package com.qwertyuiop.white.di

import com.qwertyuiop.white.ui.composables.host.mvi.HostViewModel
import com.qwertyuiop.white.ui.composables.loading.mvi.LoadingViewModel
import com.qwertyuiop.white.ui.composables.main.mvi.MainViewModel
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsViewModel
import com.qwertyuiop.white.ui.composables.start.mvi.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object WhiteModule {
    val module = module {
        viewModelOf(::HostViewModel)
        viewModelOf(::SettingsViewModel)
        viewModelOf(::LoadingViewModel)
        viewModelOf(::StartViewModel)
        viewModelOf(::MainViewModel)
    }
}