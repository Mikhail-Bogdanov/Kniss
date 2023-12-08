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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qwertyuiop.white.R
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.*
import com.qwertyuiop.white.ui.utilsUI.ConstantsUI.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeDialog(show: Boolean, onEvent: (SettingsEvent) -> Unit) {
    if (!show) return

    AlertDialog(
        onDismissRequest = {
            onEvent(HideThemeDialogClicked)
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
            repeat(Theme.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(16.dp)
                        .clickable {
                            onEvent(
                                ThemeClicked(Theme[index])
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = when (index) {
                            0 -> stringResource(R.string.dark)
                            else -> stringResource(R.string.light)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}