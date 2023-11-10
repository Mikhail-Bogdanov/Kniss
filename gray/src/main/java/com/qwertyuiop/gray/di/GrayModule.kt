package com.qwertyuiop.gray.di

import com.qwertyuiop.gray.ui.error.mvi.ErrorViewModel
import com.qwertyuiop.gray.ui.gray.mvi.GrayViewModel
import com.qwertyuiop.gray.ui.gray.utils.OneSignalHolder
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object GrayModule {
    val module = module {
        viewModel {
            ErrorViewModel(
                getSavedUrlUseCase = get(),
                saveUrlUseCase = get(),
                getServiceResponseUseCase = get()
            )
        }
        viewModel {
            GrayViewModel(
                getSavedUrlUseCase = get(),
                oneSignalHolder = get()
            )
        }
        single {
            OneSignalHolder(context = androidApplication().applicationContext)
        }
    }
}