package com.qwertyuiop.gray.ui.gray.mvi

import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher

sealed class GrayEvent {
    data object SetLoadingFalse : GrayEvent()
    data object SetLoadingTrue : GrayEvent()
    data object DisableCallback : GrayEvent()
    data class UpdateForLeakedSsl(
        val message: String
    ) : GrayEvent()

    data object RequestPermissions : GrayEvent()
    data class CheckUrlForError(
        val errorUrl: String,
        val errorName: String
    ) : GrayEvent()

    data class SetCallbackValue(
        val results: Array<Uri>?
    ) : GrayEvent()

    data class NullCallbackValue(
        val message: String
    ) : GrayEvent()

    data class EnableCallback(
        val callback: ValueCallback<Array<Uri>>
    ) : GrayEvent()

    data object Setup : GrayEvent()

    data object CreateIntent : GrayEvent()


    data class SetImg(
        val img: ActivityResultLauncher<Intent>
    ) : GrayEvent()


    data class UpdatePermissionState(
        val isGranted: Boolean
    ) : GrayEvent()

    data class ChangeToError(
        val message: String
    ) : GrayEvent()
}