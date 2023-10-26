package com.example.white.ui.composables.start.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.white.R
import com.example.white.ui.composables.start.mvi.StartEvent
import com.example.white.ui.composables.start.mvi.StartEvent.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartTopBar(onEvent: (StartEvent) -> Unit) {
    //TODO START TOP BAR
    TopAppBar(
        title = {
            Text(
                text = "",
                style = MaterialTheme.typography.titleMedium
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onEvent(SettingsButtonClicked)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}