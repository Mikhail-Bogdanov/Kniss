package com.qwertyuiop.gray.di

import com.qwertyuiop.gray.ui.error.mvi.ErrorViewModel
import com.qwertyuiop.gray.ui.gray.mvi.GrayViewModel
import com.qwertyuiop.gray.ui.gray.utils.OneSignalHolder
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object GrayModule {
    val module = module {
        viewModelOf(::ErrorViewModel)
        viewModelOf(::GrayViewModel)
        singleOf(::OneSignalHolder)
    }
}