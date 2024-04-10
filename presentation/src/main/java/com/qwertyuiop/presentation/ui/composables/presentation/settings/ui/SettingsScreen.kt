package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsState

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(horizontal = 24.dp)
        .padding(top = 24.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    LocalePicker(state = state, onEvent = onEvent)
}