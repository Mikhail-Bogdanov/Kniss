package com.qwertyuiop.white.ui.composables.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.*
import com.qwertyuiop.white.ui.composables.settings.utils.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocaleDialog(show: Boolean, onEvent: (SettingsEvent) -> Unit) {
    if (!show) return

    AlertDialog(
        onDismissRequest = {
            onEvent(HideLocaleDialogClicked)
        }
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Language.entries.forEach { language ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                        .clickable {
                            onEvent(
                                LocaleClicked(language)
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    //TODO: ADD MARKK CURRENT LANGUAGE
                    Text(
                        text = language.title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}