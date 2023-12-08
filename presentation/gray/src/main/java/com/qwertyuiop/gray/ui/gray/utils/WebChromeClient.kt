package com.qwertyuiop.gray.ui.gray.utils

import android.content.Context
import android.net.Uri
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent
import com.qwertyuiop.gray.ui.gray.mvi.GrayEvent.EnableCallback
import com.qwertyuiop.gray.utils.Constants.ok
import com.qwertyuiop.gray.utils.Constants.openGalleryException

class WebChromeClient(
    private val context: Context,
    private val onEvent: (GrayEvent) -> Unit
) : AccompanistWebChromeClient() {

    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setPositiveButton(ok, null)
            .setCancelable(false)
            .show()
        result?.confirm()
        return true
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        filePathCallback?.let {
            onEvent(EnableCallback(filePathCallback))
        }
        try {
            onEvent(GrayEvent.CreateIntent)
        } catch (e: Exception) {
            webView?.let {
                Snackbar.make(
                    webView,
                    openGalleryException,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return true
    }
}