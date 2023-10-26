package com.example.gray.ui.gray.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.example.gray.ui.gray.mvi.GrayEvent
import com.google.accompanist.web.AccompanistWebViewClient

class WebViewClient(
    private val context: Context,
    private val onEvent: (GrayEvent) -> Unit
) : AccompanistWebViewClient() {
    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        onEvent(GrayEvent.SetLoadingTrue)
    }

    override fun onPageFinished(view: WebView, url: String?) {
        super.onPageFinished(view, url)
        onEvent(GrayEvent.SetLoadingFalse)
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean = request?.let {
        val url = it.url
        return try {
            when {
                !"$url".contains("http") -> {
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    startActivity(context, intent, null)
                    true
                }

                "$url".contains("intent://") -> {
                    startActivity(context, Intent(Intent.ACTION_VIEW).also { intent ->
                        intent.data =
                            "${request.url}".replace("intent", "https").toUri()
                    }, null)
                    true
                }

                "$url".contains("http") -> {
                    false
                }

                else -> super.shouldOverrideUrlLoading(view, request)
            }
        } catch (e: Exception) {
            true
        }
    } ?: super.shouldOverrideUrlLoading(view, request)

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        val errorName = error?.description
        val url = request?.url
        errorName?.let {
            val errorUrl = url?.host
            errorUrl?.let {
                onEvent(GrayEvent.CheckUrlForError(errorUrl, errorName.toString()))
            }
        }
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        onEvent(GrayEvent.UpdateForLeakedSsl)
    }
}