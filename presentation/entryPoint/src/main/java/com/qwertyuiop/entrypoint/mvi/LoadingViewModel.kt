package com.qwertyuiop.entrypoint.mvi

import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.core.remote.RemoteUtils
import com.qwertyuiop.core.remote.RemoteUtils.Companion.URL
import com.qwertyuiop.domaingray.entities.AuthEntity
import com.qwertyuiop.domaingray.useCases.local.GetSavedUrlUseCase
import com.qwertyuiop.domaingray.useCases.local.SaveUrlUseCase
import com.qwertyuiop.domaingray.useCases.remote.GetServiceResponseUseCase
import com.qwertyuiop.entrypoint.mvi.LoadingEvent.GetRequest
import com.qwertyuiop.entrypoint.mvi.LoadingSideEffect.NavigateToGray
import com.qwertyuiop.entrypoint.mvi.LoadingSideEffect.NavigateToWhite
import kotlinx.coroutines.flow.firstOrNull
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class LoadingViewModel(
    private val getServiceResponseUseCase: GetServiceResponseUseCase,
    private val getSavedUrlUseCase: GetSavedUrlUseCase,
    private val saveUrlUseCase: SaveUrlUseCase,
    private val remoteUtils: RemoteUtils
) : MviViewModel<Any, LoadingSideEffect, LoadingEvent>(
    initialState = Any()
) {
    override fun dispatch(event: LoadingEvent) {
        when (event) {
            GetRequest -> getRequest()
        }
    }

    private suspend fun SimpleSyntax<Any, LoadingSideEffect>.changeToGray() {
        postSideEffect(NavigateToGray)
    }

    private suspend fun SimpleSyntax<Any, LoadingSideEffect>.changeToWhite() {
        postSideEffect(NavigateToWhite)
    }

    private fun getRequest() = intent {
        val savedUrl = getSavedUrlUseCase().firstOrNull()
        if (savedUrl.isNullOrEmpty()) {
            try {
                val responseUrl = getServiceResponseUseCase(
                    AuthEntity(
                        remoteUtils.ucStatus(),
                        remoteUtils.getDevice(),
                        URL
                    )
                ).answer
                if (responseUrl.isEmpty())
                    throw IllegalStateException()
                saveUrlUseCase(responseUrl)
                changeToGray()
            } catch (e: Exception) {
                changeToWhite()
            }
        } else {
            changeToGray()
        }
    }
}