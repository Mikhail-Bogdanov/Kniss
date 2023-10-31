package com.ex.gray.utils

import androidx.compose.ui.graphics.Color

object Constants {
    /**
     * Errors from WebView
     */
    const val internetDisconnected = "net::ERR_INTERNET_DISCONNECTED"
    private const val unknownUrlScheme = "net::ERR_UNKNOWN_URL_SCHEME"
    private const val connectionAborted = "net::ERR_CONNECTION_ABORTED"
    private const val timedOut = "net::ERR_TIMED_OUT"
    private const val nameNotResolved = "net::ERR_NAME_NOT_RESOLVED"
    private const val cacheMiss = "net::ERR_CACHE_MISS"
    private const val connectionClosed = "net::ERR_CONNECTION_CLOSED"
    private const val connectionTimedOut = "net::ERR_CONNECTION_TIMED_OUT"
    private const val blockedByResponse = "net::ERR_BLOCKED_BY_RESPONSE"

    /**
     * Text in ErrorScreen
     */
    private const val internetDisconnectedMessage =
        "Network connection error, connect to Internet and try again"
    private const val unknownUrlSchemeMessage = "Make sure you have the right application installed"
    private const val connectionAbortedMessage =
        "Connection aborted, check your connection and try again"
    private const val timedOutMessage = "Connection timeout, check your connection and try again"
    private const val nameNotResolvedMessage = "URL name not resolved, repeat again"
    private const val cacheMissMessage = "Missing cache, try again"
    private const val connectionClosedMessage =
        "Connection was closed, check your Internet connection and repeat again"
    private const val connectionTimedOutMessage =
        "Connection timeout, check your connection and try again"
    private const val blockedByResponseMessage = "Something was wrong, try again later"

    val hashMapErrors = hashMapOf(
        internetDisconnected to internetDisconnectedMessage,
        unknownUrlScheme to unknownUrlSchemeMessage,
        connectionAborted to connectionAbortedMessage,
        timedOut to timedOutMessage,
        nameNotResolved to nameNotResolvedMessage,
        cacheMiss to cacheMissMessage,
        connectionClosed to connectionClosedMessage,
        connectionTimedOut to connectionTimedOutMessage,
        blockedByResponse to blockedByResponseMessage,
    )

    private const val instagramUrl = "www.instagram.com"
    private const val facebookUrl = "www.facebook.com"
    private const val twitterUrl = "twitter.com"
    private const val casinomentorUrl = "casinomentor.com"
    private const val bestbitcoincasinoUrl = "www.bestbitcoincasino.com"
    private const val onewpeia = "1wpeia.top"
    const val googleUrl = "https://www.google.com"

    private const val blockedUrl = "This page is currently unavailable"

    val urlsHashMap = hashMapOf(
        instagramUrl to blockedUrl,
        facebookUrl to blockedUrl,
        twitterUrl to blockedUrl,
        casinomentorUrl to blockedUrl,
        bestbitcoincasinoUrl to blockedUrl,
        onewpeia to blockedUrl
    )

    const val sslError = "ssh_error"
    const val SslErrorMessage = "Some SSL Error"

    private const val sslErrorMessage = "The server is unavailable, try again later"

    const val permissionDeniedMes =
        "Please give permission to send\nnotifications to be aware of the latest events"

    val errorSslMap = hashMapOf(
        sslError to sslErrorMessage
    )

    const val ONE_SIGNAL_ID = "" //TODO ONE SIGNAL

    const val unknownError = "Unknown error, try to refresh page"

    const val retry = "Retry"

    const val ok = "Ok"

    const val openGalleryException = "Unable to open gallery"

    const val fileError = "FILE ERROR!"

    val BarsColor = Color(0xff090F1E)
}