package com.ex.white.ui.composables.loading.mvi

import com.ex.core.mviViewModel.MviViewModel
import com.ex.domaingray.useCases.local.GetSavedUrlUseCase
import com.ex.domaingray.useCases.local.SaveUrlUseCase
import com.ex.domaingray.useCases.remote.GetServiceResponseUseCase
import com.ex.white.ui.composables.loading.mvi.LoadingEvent.ChangeToGray
import com.ex.white.ui.composables.loading.mvi.LoadingEvent.ChangeToWhite
import com.ex.white.ui.composables.loading.mvi.LoadingEvent.GetRequest
import com.ex.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToGray
import com.ex.white.ui.composables.loading.mvi.LoadingSideEffect.NavigateToWhite
import kotlinx.coroutines.flow.firstOrNull
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