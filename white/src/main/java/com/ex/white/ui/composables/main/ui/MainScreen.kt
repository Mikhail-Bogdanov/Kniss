package com.ex.white.ui.composables.main.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ex.white.ui.composables.main.mvi.MainEvent
import com.ex.white.ui.composables.main.mvi.MainState

@Composable
fun MainScreen(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MainTopBar(onEvent = onEvent)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        MainScreenContent(
            state = state,
            paddingValues = paddingValues,
            onEvent = onEvent
        )
    }
}