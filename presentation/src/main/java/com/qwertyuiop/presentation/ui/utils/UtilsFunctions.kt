package com.qwertyuiop.presentation.ui.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_EMAIL
import android.net.Uri.parse
import androidx.browser.customtabs.CustomTabsIntent
import com.qwertyuiop.presentation.ui.utils.ConstantsUI.DeveloperGmail
import com.qwertyuiop.presentation.ui.utils.ConstantsUI.MailRequestCode
import com.qwertyuiop.presentation.ui.utils.ConstantsUI.PlayMarketUri
import com.qwertyuiop.presentation.ui.utils.ConstantsUI.WebPlayMarketUri

object UtilsFunctions {
    fun openGmail(
        context: Context,
        onError: () -> Unit
    ) {
        val intentToGmail = Intent(Intent.ACTION_SEND).setType(MailRequestCode)
            .putExtra(EXTRA_EMAIL, listOf(DeveloperGmail).toTypedArray())

        if (intentToGmail.resolveActivity(context.packageManager) == null)
            onError()
        else
            context.startActivity(intentToGmail)
    }

    fun openPlayMarket(context: Context) = try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW, parse(PlayMarketUri + context.packageName)
            )
        )
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW, parse(WebPlayMarketUri + context.packageName)
            )
        )
    }

    fun openChromeTab(context: Context, link: String) = CustomTabsIntent
        .Builder()
        .build()
        .launchUrl(context, parse(link))
}