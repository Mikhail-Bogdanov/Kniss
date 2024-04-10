package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent.ExpandLanguagesClicked
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsEvent.LanguageClicked
import com.qwertyuiop.presentation.ui.composables.presentation.settings.mvi.SettingsState
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language

@Composable
fun LocalePicker(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) = Column(
    modifier = Modifier
        .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    LanguageListItem(
        language = state.currentLanguage,
        checked = true,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onEvent(ExpandLanguagesClicked)
            }
    ) {
        val angle by animateFloatAsState(
            targetValue = when (state.isLanguagesExpanded) {
                true -> 0f
                false -> 180f
            },
            label = "",
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier
                .rotate(angle)
        )
    }
    AnimatedVisibility(visible = state.isLanguagesExpanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Language.entries.filterNot { it == state.currentLanguage }.forEach { language ->
                LanguageListItem(
                    language = language,
                    checked = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            onEvent(LanguageClicked(language))
                        }
                )
            }
        }
    }
}