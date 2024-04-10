package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language

@Composable
fun LanguageListItem(
    language: Language,
    checked: Boolean,
    modifier: Modifier = Modifier,
    trailingContent: @Composable (() -> Unit)? = null
) = ListItem(
    headlineContent = {
        Text(
            text = language.title,
            style = MaterialTheme.typography.bodyMedium
        )
    },
    leadingContent = {
        Checkbox(
            checked = checked,
            onCheckedChange = {},
            enabled = false,
            colors = CheckboxDefaults.colors(
                disabledCheckedColor = MaterialTheme.colorScheme.primary,
                disabledUncheckedColor = Color.Transparent,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    },
    trailingContent = trailingContent,
    colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.primary,
        headlineColor = MaterialTheme.colorScheme.onPrimary,
        leadingIconColor = MaterialTheme.colorScheme.onPrimary,
        trailingIconColor = MaterialTheme.colorScheme.onPrimary
    ),
    modifier = modifier
        .fillMaxWidth()
)