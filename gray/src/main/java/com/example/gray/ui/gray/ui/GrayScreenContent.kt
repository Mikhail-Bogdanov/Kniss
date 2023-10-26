package com.example.gray.ui.gray.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gray.ui.gray.mvi.GrayEvent
import com.example.gray.ui.gray.mvi.GrayState
import com.example.gray.ui.gray.mvi.GrayViewModel
import com.example.gray.ui.gray.utils.WebChromeClient
import com.example.gray.ui.gray.utils.WebViewClient
import com.example.gray.utils.ConnectionManager
import com.example.gray.utils.Constants
import com.example.gray.utils.Constants.ONE_SIGNAL_ID
import com.example.gray.utils.Constants.hashMapErrors
import com.example.gray.utils.Constants.internetDisconnected
import com.example.gray.utils.Constants.permissionDeniedMes
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.onesignal.OneSignal
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GrayScreenContent(
    paddingValues: PaddingValues,
    snackbarState: SnackbarHostState,
    grayState: GrayState,
    onEvent: (GrayEvent) -> Unit,
    context: Context = LocalContext.current
) {
    val notificationPermission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
    } else {
        null
    }

    LaunchedEffect(key1 = notificationPermission?.status?.isGranted) {
        notificationPermission?.status?.isGranted?.let {
            onEvent(
                GrayEvent.UpdatePermissionState(
                    notificationPermission.status.isGranted
                )
            )
        }
        if (notificationPermission?.status?.isGranted == false)
            snackbarState.showSnackbar(permissionDeniedMes)
    }

    if (!ConnectionManager().internet(context))
        onEvent(GrayEvent.ChangeToError(hashMapErrors.getValue(internetDisconnected)))
    else
        InitWebView(snackbarState)

    val webViewUrl = if (grayState.url != null) {
        RequestPermission(notificationPermission, grayState)
        SetOneSignal()
        grayState.url
    } else {
        Constants.googleUrl
    }

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
private fun SetOneSignal() {
    OneSignal.setLogLevel(OneSignal.LOG_LEVEL.NONE, OneSignal.LOG_LEVEL.NONE)
    OneSignal.initWithContext(LocalContext.current.applicationContext)
    OneSignal.setAppId(ONE_SIGNAL_ID)
    OneSignal.promptForPushNotifications()
}

@Composable
private fun InitWebView(
    snackbarState: SnackbarHostState,
    grayViewModel: GrayViewModel = koinViewModel()
) {
    val coroutineContext = rememberCoroutineScope()
    val img = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            when {
                grayViewModel.valueCallback != null -> {
                    result.data?.data?.let { uri ->
                        grayViewModel.dispatch(GrayEvent.SetCallbackValue(arrayOf(uri)))
                        grayViewModel.dispatch(GrayEvent.DisableCallback)
                    }
                }

                else -> {
                    coroutineContext.launch {
                        snackbarState.showSnackbar(Constants.fileError)
                    }
                }
            }
        } else {
            grayViewModel.dispatch(GrayEvent.SetCallbackValue(null))
            grayViewModel.dispatch(GrayEvent.DisableCallback)
        }
    }
    grayViewModel.dispatch(GrayEvent.SetImg(img))
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequestPermission(
    notificationPermission: PermissionState?,
    grayState: GrayState
) {
    if (!grayState.isGranted) {
        SideEffect {
            notificationPermission?.launchPermissionRequest()
        }
    }
}
