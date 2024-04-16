package com.evoteam.presentation.ui.composables.presentation.start.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.BackButtonClicked
import com.evoteam.presentation.ui.composables.presentation.start.mvi.StartEvent.SettingsClicked
import com.evoteam.presentation.ui.utils.composables.GeneralTopAppBar

@Composable
fun StartTopBar(onEvent: (StartEvent) -> Unit) = GeneralTopAppBar(
    title = stringResource(R.string.app_name),
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
        IconButton(
            onClick = {
                onEvent(SettingsClicked)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null
            )
        }
    }
)