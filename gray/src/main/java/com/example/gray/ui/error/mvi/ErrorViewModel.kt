package com.example.gray.ui.error.mvi

import com.example.domaingray.useCases.local.GetSavedUrlUseCase
import com.example.domaingray.useCases.local.SaveUrlUseCase
import com.example.domaingray.useCases.remote.GetServiceResponseUseCase
import com.example.gray.mviViewModel.MviViewModel
import com.example.gray.ui.error.mvi.ErrorSideEffect.NavigateToGray
import com.example.gray.ui.error.mvi.ErrorSideEffect.ShowSnackBar
import com.example.gray.utils.Constants.SslErrorMessage
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
            ErrorEvent.ChangeToGray -> changeToGray()
        }
    }

    private fun changeToGray() = intent {
        postSideEffect(NavigateToGray)
    }

    private fun updateRequest() = intent {
        val savedUrl = getSavedUrlUseCase().firstOrNull()
        if (savedUrl.isNullOrEmpty()) {
            try {
                val url = getServiceResponseUseCase().answer
                saveUrlUseCase(url)
                dispatch(ErrorEvent.ChangeToGray)
            } catch (_: Exception) {

            }
        } else {
            dispatch(ErrorEvent.ChangeToGray)
        }
    }

    private fun updateSslRequest() = intent {
        try {
            val url = getServiceResponseUseCase().answer
            saveUrlUseCase(url)
            dispatch(ErrorEvent.ChangeToGray)
        } catch (_: Exception) {
            postSideEffect(ShowSnackBar(SslErrorMessage))
        }
    }
}