package com.evoteam.presentation.ui.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_EMAIL
import android.net.Uri.parse
import androidx.browser.customtabs.CustomTabsIntent
import com.evoteam.domain.entities.Knitting
import com.evoteam.domain.entities.Loop
import com.evoteam.presentation.ui.utils.ConstantsUI.DeveloperGmail
import com.evoteam.presentation.ui.utils.ConstantsUI.MailRequestCode
import com.evoteam.presentation.ui.utils.ConstantsUI.PlayMarketUri
import com.evoteam.presentation.ui.utils.ConstantsUI.WebPlayMarketUri
import java.util.UUID

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

    fun generateKnitting(
        width: Int,
        height: Int,
        pattern: List<List<Loop>>,
        name: String
    ): Knitting {
        val stampHeight = pattern.size

        val stamp = MutableList(stampHeight) { rowIndex ->
            val loopsInRow = pattern[rowIndex].size
            var currentLoop = -1
            MutableList(width) {
                if (currentLoop++ == loopsInRow.minus(1)) currentLoop = 0
                pattern[rowIndex][currentLoop]
            }
        }

        var currentRow = -1
        val updatedLoops = List(height) {
            if (currentRow++ == stampHeight.minus(1)) currentRow = 0
            stamp[currentRow].map { Loop(it.type) } //to recreate object reference
        }

        return Knitting(
            id = UUID.randomUUID().toString(),
            currentRow = 0,
            width = width,
            height = height,
            loops = updatedLoops,
            name = name
        )
    }
}