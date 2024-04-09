package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent.LanguageClicked
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
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
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            items = Language.entries.toList()
        ) { language ->
            PrimaryButton(
                text = language.title
            ) {
                onEvent(LanguageClicked(language))
            }
        }
    }
}