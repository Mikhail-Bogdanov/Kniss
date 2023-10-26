package com.example.white.ui.utilsUI

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri.parse
import androidx.browser.customtabs.CustomTabsIntent
import com.example.white.ui.utilsUI.ConstantsUI.DeveloperGmail
import com.example.white.ui.utilsUI.ConstantsUI.MailRequestCode
import com.example.white.ui.utilsUI.ConstantsUI.PlayMarketUri
import com.example.white.ui.utilsUI.ConstantsUI.PolicyLink
import com.example.white.ui.utilsUI.ConstantsUI.TermsLink
import com.example.white.ui.utilsUI.ConstantsUI.WebPlayMarketUri

object UtilsFunctions {
    fun openGmail(
        context: Context,
        onError: () -> Unit
    ) {
        val intentToGmail = Intent(Intent.ACTION_SEND)
            .setType(MailRequestCode)
            .putExtra(Intent.EXTRA_EMAIL, listOf(DeveloperGmail).toTypedArray())

        if (intentToGmail.resolveActivity(context.packageManager) == null)
            onError()
        else
            context.startActivity(intentToGmail)
    }

    fun openPlayMarket(context: Context) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    parse(PlayMarketUri + context.packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    parse(WebPlayMarketUri + context.packageName)
                )
            )
        }
    }

    fun openWebView(context: Context, argument: NavigationArguments) {
        val intentBuilder = CustomTabsIntent
            .Builder()
            .build()

        when (argument) {
            NavigationArguments.Policy -> {
                intentBuilder.launchUrl(context, parse(PolicyLink))
            }

            NavigationArguments.Terms -> {
                intentBuilder.launchUrl(context, parse(TermsLink))
            }
        }
    }
}