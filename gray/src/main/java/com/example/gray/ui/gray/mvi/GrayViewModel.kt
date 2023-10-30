package com.example.gray.ui.gray.mvi

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.viewModelScope
import com.example.core.mviViewModel.MviViewModel
import com.example.domaingray.useCases.local.GetSavedUrlUseCase
import com.example.gray.ui.gray.mvi.GrayEvent.ChangeToError
import com.example.gray.ui.gray.mvi.GrayEvent.CheckUrlForError
import com.example.gray.ui.gray.mvi.GrayEvent.CreateIntent
import com.example.gray.ui.gray.mvi.GrayEvent.DisableCallback
import com.example.gray.ui.gray.mvi.GrayEvent.EnableCallback
import com.example.gray.ui.gray.mvi.GrayEvent.SetCallbackValue
import com.example.gray.ui.gray.mvi.GrayEvent.SetImg
import com.example.gray.ui.gray.mvi.GrayEvent.SetLoadingFalse
import com.example.gray.ui.gray.mvi.GrayEvent.SetLoadingTrue
import com.example.gray.ui.gray.mvi.GrayEvent.Setup
import com.example.gray.ui.gray.mvi.GrayEvent.UpdateForLeakedSsl
import com.example.gray.ui.gray.mvi.GrayEvent.UpdatePermissionState
import com.example.gray.ui.gray.mvi.GraySideEffect.NavigateToError
import com.example.gray.ui.gray.mvi.GraySideEffect.RequestPermissions
import com.example.gray.ui.gray.mvi.GraySideEffect.ShowSnackbar
import com.example.gray.utils.Constants.ONE_SIGNAL_ID
import com.example.gray.utils.Constants.errorSslMap
import com.example.gray.utils.Constants.googleUrl
import com.example.gray.utils.Constants.hashMapErrors
import com.example.gray.utils.Constants.permissionDeniedMes
import com.example.gray.utils.Constants.urlsHashMap
import com.onesignal.OneSignal
import com.onesignal.OneSignal.LOG_LEVEL.NONE
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class GrayViewModel(
    private val getSavedUrlUseCase: GetSavedUrlUseCase
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
            is Setup -> setup(event.context)
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

    private fun setOneSignal(context: Context) {
        OneSignal.setLogLevel(NONE, NONE)
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ONE_SIGNAL_ID)
        OneSignal.promptForPushNotifications()
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

    private fun setup(context: Context) = intent {
        getSavedUrlUseCase().onEach { url ->
            if (url != null)
                setOneSignal(context)
            reduce {
                state.copy(
                    url = url,
                    webViewUrl = url ?: googleUrl
                )
            }
        }.launchIn(viewModelScope)
    }
}