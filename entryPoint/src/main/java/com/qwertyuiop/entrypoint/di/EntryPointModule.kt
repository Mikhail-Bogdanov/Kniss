package com.qwertyuiop.entrypoint.di

import com.qwertyuiop.entrypoint.ui.components.mainActivity.mvi.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object EntryPointModule {

    val module = module {
        viewModelOf(::MainActivityViewModel)
    }

}