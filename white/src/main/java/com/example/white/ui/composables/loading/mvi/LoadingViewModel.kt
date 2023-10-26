package com.example.white.ui.composables.loading.mvi

import androidx.lifecycle.viewModelScope
import com.example.domaingray.useCases.local.GetSavedUrlUseCase
import com.example.domaingray.useCases.local.SaveUrlUseCase
import com.example.domaingray.useCases.remote.GetServiceResponseUseCase
import com.example.white.ui.composables.loading.mvi.LoadingEvent.ChangeToGray
import com.example.white.ui.composables.loading.mvi.LoadingEvent.ChangeToWhite
import com.example.white.ui.composables.loading.mvi.LoadingEvent.GetRequest
import com.example.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToGray
import com.example.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToWhite
import com.example.white.ui.mviViewModel.MviViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class LoadingViewModel(
    private val getServiceResponseUseCase: GetServiceResponseUseCase,
    private val getSavedUrlUseCase: GetSavedUrlUseCase,
    private val saveUrlUseCase: SaveUrlUseCase
) : MviViewModel<Any, LoadingSideEffect, LoadingEvent>(
    initialState = Any()
) {
    override fun dispatch(event: LoadingEvent) {
        when (event) {
            ChangeToGray -> changeToGray()
            GetRequest -> getRequest()
            ChangeToWhite -> changeToWhite()
        }
    }

    private fun changeToGray() = intent {
        postSideEffect(NavigateToGray)
    }

    private fun changeToWhite() = intent {
        postSideEffect(NavigateToWhite)
    }

    private fun getRequest() = intent {
        viewModelScope.launch {
            val savedUrl = getSavedUrlUseCase().firstOrNull()
            if (savedUrl.isNullOrEmpty()) {
                try {
                    val responseUrl = getServiceResponseUseCase().answer
                    if (responseUrl.isEmpty())
                        throw IllegalStateException()
                    saveUrlUseCase(responseUrl)
                    dispatch(ChangeToGray)
                } catch (e: Exception) {
                    dispatch(ChangeToWhite)
                }
            } else {
                dispatch(ChangeToGray)
            }
        }
    }
}