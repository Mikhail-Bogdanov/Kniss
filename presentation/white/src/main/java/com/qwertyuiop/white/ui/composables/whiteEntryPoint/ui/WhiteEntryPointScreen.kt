package com.qwertyuiop.white.ui.composables.whiteEntryPoint.ui

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun WhiteEntryPointScreen() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .aspectRatio(1f),
        color = MaterialTheme.colorScheme.primary,
        trackColor = Color.Transparent,
        strokeWidth = 4.dp,
        strokeCap = StrokeCap.Round
    )
}