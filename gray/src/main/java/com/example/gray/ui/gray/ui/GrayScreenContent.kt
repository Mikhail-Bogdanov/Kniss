package com.example.gray.ui.gray.ui

import android.Manifest.permission.POST_NOTIFICATIONS
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gray.ui.gray.mvi.GrayEvent
import com.example.gray.ui.gray.mvi.GrayEvent.*
import com.example.gray.ui.gray.mvi.GrayState
import com.example.gray.ui.gray.utils.WebChromeClient
import com.example.gray.ui.gray.utils.WebViewClient
import com.example.gray.utils.ConnectionManager
import com.example.gray.utils.Constants.fileError
import com.example.gray.utils.Constants.googleUrl
import com.example.gray.utils.Constants.hashMapErrors
import com.example.gray.utils.Constants.internetDisconnected
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GrayScreenContent(
    paddingValues: PaddingValues,
    grayState: GrayState,
    onEvent: (GrayEvent) -> Unit,
    context: Context = LocalContext.current
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(Setup(context))
    }

    CheckPermissions(onEvent)

    val img = createImg(
        onEvent = onEvent,
        state = grayState
    )

    LaunchedEffect(key1 = Unit) {
        when (ConnectionManager().internet(context)) {
            true -> onEvent(
                SetImg(img)
            )

            false -> onEvent(
                ChangeToError(
                    hashMapErrors.getValue(internetDisconnected)
                )
            )
        }
    }

    val webViewUrl = grayState.url ?: googleUrl

    val webViewState = rememberWebViewState(url = webViewUrl)

    if (grayState.isLoading)
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .aspectRatio(1f),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.background,
            strokeWidth = 4.dp
        )
    WebView(
        state = webViewState,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        onCreated = { webView ->
            var userAgent: String = webView.settings.userAgentString
            val os = userAgent.substring(userAgent.indexOf("("), userAgent.indexOf(")") + 1)
            userAgent = userAgent.replace(
                os,
                "(Linux; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + ")"
            )
            webView.settings.userAgentString = userAgent
            webView.isVerticalScrollBarEnabled = true
            webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webView.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                useWideViewPort = true
                loadWithOverviewMode = true
                cacheMode = WebSettings.LOAD_NO_CACHE
            }
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        },
        chromeClient = WebChromeClient(context, onEvent),
        client = WebViewClient(context, onEvent)
    )
}

@Composable
private fun createImg(
    onEvent: (GrayEvent) -> Unit,
    state: GrayState
) = rememberLauncherForActivityResult(
    contract = StartActivityForResult()
) { result ->
    if (result.resultCode == RESULT_OK) {
        when {
            state.valueCallback != null -> {
                result.data?.data?.let { uri ->
                    onEvent(SetCallbackValue(arrayOf(uri)))
                    onEvent(DisableCallback)
                }
            }

            else -> {
                onEvent(NullCallbackValue(fileError))
            }
        }
    } else {
        onEvent(SetCallbackValue(null))
        onEvent(DisableCallback)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CheckPermissions(
    onEvent: (GrayEvent) -> Unit
) {
    val notificationPermission = if (SDK_INT >= TIRAMISU) {
        rememberPermissionState(POST_NOTIFICATIONS)
    } else {
        null
    }

    LaunchedEffect(key1 = notificationPermission?.status?.isGranted) {
        notificationPermission?.status?.isGranted?.let {
            onEvent(
                UpdatePermissionState(
                    notificationPermission.status.isGranted
                )
            )
        }
    }
}
