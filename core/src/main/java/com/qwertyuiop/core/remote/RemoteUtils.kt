package com.qwertyuiop.core.remote

import android.content.Context
import android.content.IntentFilter
import android.os.Build.BRAND
import android.os.Build.DEVICE
import android.os.Build.MANUFACTURER
import android.os.Build.MODEL

class RemoteUtils(
    private val appContext: Context
) {

    companion object {
        //TODO: PAGE
        const val URL = "tapps2"

        private const val ACTION = "android.hardware.usb.action.USB_STATE"
        private const val RESULT = "connected"
    }

    fun ucStatus() = appContext.registerReceiver(
        null,
        IntentFilter(ACTION)
    )?.extras?.getBoolean(RESULT) ?: false

    fun getDevice(): String = "$MANUFACTURER $MODEL $BRAND $DEVICE"
}