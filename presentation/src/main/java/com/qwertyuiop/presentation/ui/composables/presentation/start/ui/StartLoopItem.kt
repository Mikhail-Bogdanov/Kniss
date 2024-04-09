package com.qwertyuiop.presentation.ui.composables.presentation.start.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.domain.entities.Loop.LoopType.Back
import com.qwertyuiop.domain.entities.Loop.LoopType.Front

@Composable
fun StartLoopItem(loop: Loop, onLoopClick: () -> Unit, onRemoveClick: () -> Unit) = Column(
    modifier = Modifier
        .size(64.dp)
        .clip(MaterialTheme.shapes.medium)
        .pointerInput(System.currentTimeMillis()) {
            detectTapGestures(
                onTap = {
                    onLoopClick()
                },
                onLongPress = {
                    onRemoveClick()
                }
            )
        }
        .background(MaterialTheme.colorScheme.primary),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly
) {
    repeat(
        when (loop.type) {
            Front -> 1
            Back -> 2
        }
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}