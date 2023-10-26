package com.example.gray.ui.error.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.gray.ui.error.mvi.ErrorEvent

@Composable
fun ErrorScreen(
    message: String,
    onEvent: (ErrorEvent) -> Unit
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        ErrorScreenContent(
            paddingValues = paddingValues,
            errorMsg = message,
            snackBarHostState = snackBarHostState,
            onEvent = onEvent
        )
    }
}