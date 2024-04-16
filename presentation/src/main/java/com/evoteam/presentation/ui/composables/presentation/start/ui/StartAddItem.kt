package com.evoteam.presentation.ui.composables.presentation.start.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.evoteam.presentation.ui.utils.composables.ComposableConstants.LoopItemSize

@Composable
fun StartAddItem(modifier: Modifier = Modifier, onClick: () -> Unit) = Box(
    modifier = Modifier
        .size(LoopItemSize)
        .then(modifier)
        .clip(MaterialTheme.shapes.medium)
        .clickable(onClick = onClick)
        .background(MaterialTheme.colorScheme.secondary),
    contentAlignment = Alignment.Center
) {
    Icon(
        imageVector = Icons.Default.Add,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onSecondary
    )
}