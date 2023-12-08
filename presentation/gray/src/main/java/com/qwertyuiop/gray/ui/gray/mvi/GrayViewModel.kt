package com.qwertyuiop.gray.ui.gray.mvi

import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.viewModelScope
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domaingray.useCases.local.GetSavedUrlUseCase
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.ChangeToError
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.CheckUrlForError
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.CreateIntent
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.DisableCallback
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.EnableCallback
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.SetCallbackValue
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.SetImg
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.SetLoadingFalse
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.SetLoadingTrue
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.Setup
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.UpdateForLeakedSsl
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.UpdatePermissionState
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.NavigateToError
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.RequestPermissions
import com.qwertyuiop.gray.ui.gray.mvi.GraySideEffect.ShowSnackbar
import com.qwertyuiop.gray.ui.gray.utils.OneSignalHolder
import com.qwertyuiop.gray.utils.Constants.errorSslMap
import com.qwertyuiop.gray.utils.Constants.googleUrl
import com.qwertyuiop.gray.utils.Constants.hashMapErrors
import com.qwertyuiop.gray.utils.Constants.permissionDeniedMes
import com.qwertyuiop.gray.utils.Constants.urlsHashMap
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class GrayViewModel(
    private val getSavedUrlUseCase: GetSavedUrlUseCase,
    private val oneSignalHolder: OneSignalHolder
) : MviViewModel<GrayState, GraySideEffect, GrayEvent>(
    initialState = GrayState()
) {
    override fun dispatch(event: GrayEvent) {
        when (event) {
            SetLoadingFalse -> setLoadingFalse()
            SetLoadingTrue -> setLoadingTrue()
            is CheckUrlForError -> checkUrlForError(event.errorUrl, event.errorName)
            DisableCallback -> disableCallback()
            is EnableCallback -> enableCallback(event.callback)
            is SetCallbackValue -> setCallbackValue(event.results)
            is UpdateForLeakedSsl -> updateForLeakedSsl(event.message)
            Setup -> setup()
            CreateIntent -> createIntent()
            is SetImg -> setImg(event.img)
            is UpdatePermissionState -> updatePermissionState(event.isGranted)
            is ChangeToError -> changeToError(event.message)
            GrayEvent.RequestPermissions -> requestPermissions()
            is GrayEvent.NullCallbackValue -> nullCallbackValue(event.message)
        }
    }

    private fun nullCallbackValue(message: String) = intent {
        postSideEffect(ShowSnackbar(message))
    }

    private fun requestPermissions() = intent {
        postSideEffect(RequestPermissions)
    }

    private fun changeToError(message: String) = intent {
        changeToError(message)
    }

    private suspend fun SimpleSyntax<GrayState, GraySideEffect>.changeToError(message: String) {
        postSideEffect(NavigateToError(message))
    }

    private fun updatePermissionState(isGranted: Boolean) = intent {
        reduce { state.copy(isGranted = isGranted) }
        if (!isGranted)
            postSideEffect(ShowSnackbar(permissionDeniedMes))
    }

    private fun setImg(newImg: ActivityResultLauncher<Intent>) = intent {
        reduce { state.copy(img = newImg) }
    }

    private fun createIntent() = intent {
        val intent = Intent(ACTION_PICK, EXTERNAL_CONTENT_URI)
        state.img?.launch(intent)
    }

    private fun setLoadingFalse() = intent {
        reduce {
            state.copy(
                isLoading = false
            )
        }
    }

    private fun setLoadingTrue() = intent {
        reduce {
            state.copy(
                isLoading = true
            )
        }
    }

    private fun enableCallback(callback: ValueCallback<Array<Uri>>) = intent {
        reduce { state.copy(valueCallback = callback) }
    }

    private fun setCallbackValue(results: Array<Uri>?) = intent {
        state.valueCallback?.onReceiveValue(results)
    }

    private fun disableCallback() = intent {
        reduce { state.copy(valueCallback = null) }
    }

    private fun checkUrlForError(errorUrl: String, errorName: String) = intent {
        val httpUrl = getSavedUrlUseCase().firstOrNull()?.toHttpUrl()?.host
        if (!httpUrl.isNullOrEmpty()) {
            try {
                if (errorUrl == httpUrl) {
                    changeToError(hashMapErrors.getValue(errorName))
                } else {
                    changeToError(urlsHashMap.getValue(httpUrl))
                }
            } catch (_: Exception) {
                if (urlsHashMap.containsKey(errorUrl))
                    changeToError(urlsHashMap.getValue(httpUrl))
            }
        }
    }

    private fun updateForLeakedSsl(message: String) = intent {
        changeToError(errorSslMap.getValue(message))
    }

    private fun setup() = intent {
        getSavedUrlUseCase().onEach { url ->
            if (url != null)
                oneSignalHolder.setupOneSignal()
            reduce {
                state.copy(
                    url = url,
                    webViewUrl = url ?: googleUrl
                )
            }
        }.launchIn(viewModelScope)
    }
}