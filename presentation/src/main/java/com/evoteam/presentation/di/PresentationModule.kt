package com.evoteam.presentation.di

import android.os.Vibrator
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingViewModel
import com.evoteam.presentation.ui.composables.presentation.menu.mvi.MenuViewModel
import com.evoteam.presentation.ui.composables.presentation.settings.mvi.SettingsViewModel
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartViewModel
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object PresentationModule {
    val module = module {
        viewModelOf(::KnittingViewModel)
        viewModelOf(::StartViewModel)
        viewModelOf(::SettingsViewModel)
        viewModelOf(::WelcomeViewModel)
        viewModelOf(::MenuViewModel)
        single {
            androidContext().getSystemService(Vibrator::class.java)
        }
    }
}