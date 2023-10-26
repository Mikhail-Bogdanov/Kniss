package com.example.gray.ui.gray.mvi

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.viewModelScope
import com.example.domaingray.useCases.local.GetSavedUrlUseCase
import com.example.gray.mviViewModel.MviViewModel
import com.example.gray.ui.gray.mvi.GrayEvent.ChangeToError
import com.example.gray.utils.Constants
import com.example.gray.utils.Constants.errorSslMap
import com.example.gray.utils.Constants.hashMapErrors
import com.example.gray.utils.Constants.urlsHashMap
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class GrayViewModel(
    private val getSavedUrlUseCase: GetSavedUrlUseCase
) : MviViewModel<GrayState, GraySideEffect, GrayEvent>(
    initialState = GrayState()
) {
    private lateinit var img: ActivityResultLauncher<Intent>

    var valueCallback: ValueCallback<Array<Uri>>? = null

    override fun dispatch(event: GrayEvent) {
        when (event) {
            GrayEvent.SetLoadingFalse -> setLoadingFalse()
            GrayEvent.SetLoadingTrue -> setLoadingTrue()
            is GrayEvent.CheckUrlForError -> checkUrlForError(event.errorUrl, event.errorName)
            GrayEvent.DisableCallback -> disableCallback()
            is GrayEvent.EnableCallback -> enableCallback(event.callback)
            is GrayEvent.SetCallbackValue -> setCallbackValue(event.results)
            GrayEvent.UpdateForLeakedSsl -> updateForLeakedSsl()
            GrayEvent.SetUrl -> setUrl()
            GrayEvent.CreateIntent -> intent()
            is GrayEvent.SetImg -> setImg(event.img)
            is GrayEvent.UpdatePermissionState -> updatePermissionState(event.isGranted)
            is ChangeToError -> changeToError(event.message)
        }
    }

    private fun changeToError(message: String) = intent {
        postSideEffect(GraySideEffect.NavigateToError(message))
    }

    private fun updatePermissionState(isGranted: Boolean) = intent {
        reduce {
            state.copy(
                isGranted = isGranted
            )
        }
    }

    private fun setImg(newImg: ActivityResultLauncher<Intent>) {
        img = newImg
    }

    private fun intent() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        img.launch(intent)
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

    private fun enableCallback(callback: ValueCallback<Array<Uri>>) {
        valueCallback = callback
    }

    private fun setCallbackValue(results: Array<Uri>?) {
        valueCallback?.onReceiveValue(results)
    }

    private fun disableCallback() {
        valueCallback = null
    }

    private fun checkUrlForError(errorUrl: String, errorName: String) {
        viewModelScope.launch {
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
    }

    private fun updateForLeakedSsl() {
        viewModelScope.launch {
            dispatch(ChangeToError(errorSslMap.getValue(Constants.sslError)))
        }
    }

    private fun setUrl() = intent {
        getSavedUrlUseCase().onEach { url ->
            reduce { state.copy(url = url) }
        }.launchIn(viewModelScope)
    }

    init {
        dispatch(GrayEvent.SetUrl)
    }
}