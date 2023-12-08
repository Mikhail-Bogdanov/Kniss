package com.qwertyuiop.appentrypoint.di

import com.qwertyuiop.appentrypoint.ui.components.mainActivity.mvi.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object EntryPointModule {

    val module = module {
        viewModelOf(::MainActivityViewModel)
    }

}