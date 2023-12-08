package com.qwertyuiop.gray.ui.error.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qwertyuiop.gray.ui.error.mvi.ErrorEvent

@Composable
fun ErrorScreen(
    message: String,
    onEvent: (ErrorEvent) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        ErrorScreenContent(
            paddingValues = paddingValues,
            errorMsg = message,
            onEvent = onEvent
        )
    }
}