package com.qwertyuiop.core.remote

import android.content.Context

class RemoteUtils(
    private val appContext: Context
) {

    companion object {
        //TODO PAGE
        const val URL = "tapps2"

        private const val ACTION = "android.hardware.usb.action.USB_STATE"
        private const val RESULT = "connected"
    }

    //TODO STATUS && DEVICE
    fun ucStatus() = false
    fun getDevice(): String = "Test device"


//    fun ucStatus() = appContext.registerReceiver(
//        null,
//        IntentFilter(ACTION)
//    )?.extras?.getBoolean(RESULT) ?: false
//
//    fun getDevice(): String = "$MANUFACTURER $MODEL $BRAND $DEVICE"
}