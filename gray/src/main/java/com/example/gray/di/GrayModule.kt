package com.example.gray.di

import com.example.gray.ui.error.mvi.ErrorViewModel
import com.example.gray.ui.gray.mvi.GrayViewModel
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
                getSavedUrlUseCase = get()
            )
        }
    }
}