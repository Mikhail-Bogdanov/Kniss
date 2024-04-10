package com.qwertyuiop.presentation.ui.utils.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalScrollBox(
    horizontalScrollState: ScrollState = rememberScrollState(),
    alignment: Alignment = Alignment.Center,
    content: @Composable BoxScope.() -> Unit
) = Box(
    modifier = Modifier
        .clip(MaterialTheme.shapes.medium)
        .border(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onBackground,
            shape = MaterialTheme.shapes.medium
        )
        .padding(2.dp)
        .fillMaxWidth()
        .horizontalScroll(horizontalScrollState),
    contentAlignment = alignment,
    content = content
)