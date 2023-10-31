package com.ex.gray.ui.gray.mvi

import android.content.Intent
import android.net.Uri
import android.webkit.ValueCallback
import androidx.activity.result.ActivityResultLauncher


data class GrayState(
    val isLoading: Boolean = true,
    val isGranted: Boolean = false,
    val url: String? = null,
    var valueCallback: ValueCallback<Array<Uri>>? = null,
    val img: ActivityResultLauncher<Intent>? = null,
    val webViewUrl: String = ""
)
