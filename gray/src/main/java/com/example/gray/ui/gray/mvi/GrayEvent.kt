package com.example.gray.ui.gray.mvi

import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.Keep

sealed class GrayEvent {
    data object SetLoadingFalse : GrayEvent()
    data object SetLoadingTrue : GrayEvent()
    data object DisableCallback : GrayEvent()
    data object UpdateForLeakedSsl : GrayEvent()

    @Keep
    data class CheckUrlForError(
        val errorUrl: String,
        val errorName: String
    ) : GrayEvent()

    @Keep
    data class SetCallbackValue(
        val results: Array<Uri>?
    ) : GrayEvent()

    @Keep
    data class EnableCallback(
        val callback: ValueCallback<Array<Uri>>
    ) : GrayEvent()

    data object SetUrl : GrayEvent()
    data object CreateIntent : GrayEvent()

    @Keep
    data class SetImg(
        val img: ActivityResultLauncher<Intent>
    ) : GrayEvent()

    @Keep
    data class UpdatePermissionState(
        val isGranted: Boolean
    ) : GrayEvent()

    data class ChangeToError(
        val message: String
    ) : GrayEvent()
}