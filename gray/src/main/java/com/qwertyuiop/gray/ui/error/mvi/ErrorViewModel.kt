package com.qwertyuiop.gray.ui.error.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domaingray.useCases.local.GetSavedUrlUseCase
import com.qwertyuiop.domaingray.useCases.local.SaveUrlUseCase
import com.qwertyuiop.domaingray.useCases.remote.GetServiceResponseUseCase
import com.qwertyuiop.gray.ui.error.mvi.ErrorSideEffect.NavigateToGray
import com.qwertyuiop.gray.ui.error.mvi.ErrorSideEffect.ShowSnackBar
import com.qwertyuiop.gray.utils.Constants.SslErrorMessage
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class ErrorViewModel(
    private val getSavedUrlUseCase: GetSavedUrlUseCase,
    private val getServiceResponseUseCase: GetServiceResponseUseCase,
    private val saveUrlUseCase: SaveUrlUseCase
) : MviViewModel<Any, ErrorSideEffect, ErrorEvent>(
    initialState = Any()
) {
    override fun dispatch(event: ErrorEvent) {
        when (event) {
            ErrorEvent.UpdateRequest -> updateRequest()
            is ErrorEvent.UpdateSslRequest -> updateSslRequest()
        }
    }
    private fun updateRequest() = intent {
        val savedUrl = getSavedUrlUseCase().firstOrNull()
        if (savedUrl.isNullOrEmpty()) {
            try {
                val url = getServiceResponseUseCase().answer
                saveUrlUseCase(url)
                postSideEffect(NavigateToGray)
            } catch (_: Exception) {
            }
        } else {
            postSideEffect(NavigateToGray)
        }
    }

    private fun updateSslRequest() = intent {
        try {
            val url = getServiceResponseUseCase().answer
            saveUrlUseCase(url)
            postSideEffect(NavigateToGray)
        } catch (_: Exception) {
            postSideEffect(ShowSnackBar(SslErrorMessage))
        }
    }
}