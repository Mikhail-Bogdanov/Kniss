package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent.BackButtonClicked
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent.ThemeClicked
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsState
import com.qwertyuiop.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun SettingsTopBar(state: SettingsState, onEvent: (SettingsEvent) -> Unit) = GeneralTopAppBar(
    title = stringResource(R.string.settings),
    navigationIcon = {
        IconButton(
            onClick = {
                onEvent(BackButtonClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    },
    actions = {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.light_theme_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
        Switch(
            checked = state.isDarkTheme,
            onCheckedChange = { isDarkTheme ->
                onEvent(ThemeClicked(isDarkTheme))
            },
            colors = SwitchDefaults.colors(
                checkedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                checkedTrackColor = MaterialTheme.colorScheme.onSecondary,
                checkedThumbColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedBorderColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedThumbColor = MaterialTheme.colorScheme.inversePrimary,
                uncheckedTrackColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.dark_theme_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 4.dp)
        )
    }
)