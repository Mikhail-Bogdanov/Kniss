package com.example.gray.ui.gray.mvi

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
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
import com.example.gray.utils.Constants
import com.example.gray.utils.Constants.ONE_SIGNAL_ID
import com.example.gray.utils.Constants.errorSslMap
import com.example.gray.utils.Constants.hashMapErrors
import com.example.gray.utils.Constants.permissionDeniedMes
import com.example.gray.utils.Constants.urlsHashMap
import com.onesignal.OneSignal
import com.onesignal.OneSignal.LOG_LEVEL.NONE
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.HttpUrl.Companion.toHttpUrl
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
            UpdateForLeakedSsl -> updateForLeakedSsl()
            is Setup -> setup(event.context)
            CreateIntent -> intent()
            is SetImg -> setImg(event.img)
            is UpdatePermissionState -> updatePermissionState(event.isGranted)
            is ChangeToError -> changeToError(event.message)
            GrayEvent.RequestPermissions -> requestPermissions()
            is GrayEvent.SetOneSignal -> setOneSignal(event.context)
            is GrayEvent.NullCallbackValue -> nullCallbackValue(event.message)
        }
    }

    private fun nullCallbackValue(message: String) = intent {
        postSideEffect(ShowSnackbar(message))
    }

    private fun requestPermissions() = intent {
        postSideEffect(RequestPermissions)
    }

    private fun setOneSignal(context: Context) = intent {
        OneSignal.setLogLevel(NONE, NONE)
        OneSignal.initWithContext(context)
        OneSignal.setAppId(ONE_SIGNAL_ID)
        OneSignal.promptForPushNotifications()
    }

    private fun changeToError(message: String) = intent {
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

    private fun intent() = intent {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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
                    dispatch(ChangeToError(hashMapErrors.getValue(errorName)))
                } else {
                    dispatch(ChangeToError(urlsHashMap.getValue(httpUrl)))
                }
            } catch (_: Exception) {
                if (urlsHashMap.containsKey(errorUrl))
                    dispatch(ChangeToError(urlsHashMap.getValue(httpUrl)))
            }
        }
    }

    private fun updateForLeakedSsl() {
        dispatch(ChangeToError(errorSslMap.getValue(Constants.sslError)))
    }

    private fun setup(context: Context) = intent {
        getSavedUrlUseCase().onEach { url ->
            reduce {
                state.copy(
                    url = url,
                    webViewUrl = if (url != null) {
                        dispatch(GrayEvent.SetOneSignal(context))
                        url
                    } else {
                        Constants.googleUrl
                    }
                )
            }
        }.launchIn(viewModelScope)
    }
}