package com.qwertyuiop.gray.ui.gray.utils

import android.content.Context
import com.qwertyuiop.gray.utils.Constants
import com.onesignal.OneSignal.LOG_LEVEL
import com.onesignal.OneSignal.initWithContext
import com.onesignal.OneSignal.promptForPushNotifications
import com.onesignal.OneSignal.setAppId
import com.onesignal.OneSignal.setLogLevel

class OneSignalHolder(
    private val context: Context
) {
    fun setupOneSignal() {
        setLogLevel(LOG_LEVEL.NONE, LOG_LEVEL.NONE)
        initWithContext(context)
        setAppId(Constants.ONE_SIGNAL_ID)
        promptForPushNotifications()
    }
}