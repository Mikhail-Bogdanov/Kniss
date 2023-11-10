package com.qwertyuiop.white.ui.composables.settings.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsState

@Composable
fun SettingsScreen(
    snackBarHostState: SnackbarHostState,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SettingsTopBar(onEvent)
        },
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        SettingsScreenContent(paddingValues, onEvent, state)
    }
}