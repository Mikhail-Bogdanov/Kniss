package com.qwertyuiop.presentation.ui.utils.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.presentation.ui.composables.presentation.shared.loopType.bigCircleState
import com.qwertyuiop.presentation.ui.composables.presentation.shared.loopType.smallCircleState

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

@Composable
fun LoopType(loopType: Loop.LoopType, color: Color) {
    //don't know is this okay :/
    val bigCircleState = bigCircleState()
    val smallCircleState = smallCircleState(loopType)

    Spacer(
        modifier = Modifier
            .size(20.dp)
            .drawWithCache {
                onDrawBehind {
                    drawCircle(
                        color = color,
                        radius = bigCircleState.radius,
                        center = bigCircleState.center,
                        style = Stroke(width = bigCircleState.width)
                    )

                    drawCircle(
                        color = color,
                        radius = smallCircleState.radius,
                        center = smallCircleState.center
                    )
                }
            }
    )
}