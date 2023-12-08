package com.qwertyuiop.di

import com.qwertyuiop.entrypoint.mvi.LoadingViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object EntryPointModule {

    val module = module {
        viewModelOf(::LoadingViewModel)
    }

}