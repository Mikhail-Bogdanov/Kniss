package com.ex.white.ui.composables.start.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ex.white.ui.composables.start.mvi.StartEvent
import com.ex.white.ui.composables.start.mvi.StartState

@Composable
fun StartScreen(
    state: StartState,
    onEvent: (StartEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            StartTopBar(onEvent = onEvent)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        StartScreenContent(
            state = state,
            paddingValues = paddingValues,
            onEvent = onEvent
        )
    }
}