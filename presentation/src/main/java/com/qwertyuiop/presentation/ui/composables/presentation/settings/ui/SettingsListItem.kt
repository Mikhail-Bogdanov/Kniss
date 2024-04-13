package com.qwertyuiop.presentation.ui.composables.presentation.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape

@Composable
fun SettingsListItem(
    title: String,
    modifier: Modifier = Modifier,
    shape: Shape = ListItemDefaults.shape,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) = ListItem(
    headlineContent = {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium
        )
    },
    leadingContent = leadingContent,
    trailingContent = trailingContent,
    colors = ListItemDefaults.colors(
        containerColor = MaterialTheme.colorScheme.primary,
        headlineColor = MaterialTheme.colorScheme.onPrimary,
        leadingIconColor = MaterialTheme.colorScheme.onPrimary,
        trailingIconColor = MaterialTheme.colorScheme.onPrimary
    ),
    modifier = modifier
        .fillMaxWidth()
        .clip(shape)
        .clickable(enabled = onClick != null, onClick = onClick ?: {})
)