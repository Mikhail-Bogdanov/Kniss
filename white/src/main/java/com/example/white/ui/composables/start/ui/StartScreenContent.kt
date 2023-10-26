package com.example.white.ui.composables.start.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.white.ui.composables.start.mvi.StartEvent
import com.example.white.ui.composables.start.mvi.StartState

@Composable
fun StartScreenContent(
    state: StartState,
    paddingValues: PaddingValues,
    onEvent: (StartEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

    }
}