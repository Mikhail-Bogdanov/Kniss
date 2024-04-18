package com.evoteam.presentation.ui.utils.composables

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.evoteam.domain.entities.Loop
import com.evoteam.domain.entities.Loop.LoopType.Back
import com.evoteam.domain.entities.Loop.LoopType.Front
import com.evoteam.presentation.ui.composables.presentation.shared.loopType.bigCircleState
import com.evoteam.presentation.ui.composables.presentation.shared.loopType.smallCircleState

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
fun LoopType(loopType: Loop.LoopType, color: Color, size: Dp) {
    //don't know is this okay :/
    val bigCircleState = bigCircleState(size)
    val smallCircleState = smallCircleState(loopType, size)

    Spacer(
        modifier = Modifier
            .size(size)
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

@Composable
fun LoopTypeDropdownMenu(expanded: Boolean, onDismissRequest: () -> Unit) = DropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismissRequest,
    modifier = Modifier
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 4.dp),
    offset = DpOffset(
        x = 0.dp,
        y = 12.dp
    )
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Loop.LoopType.entries.forEach { type ->
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (type) {
                        Front -> stringResource(R.string.knit_stitch)
                        Back -> stringResource(R.string.purl_stitch)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = stringResource(R.string.space_is_space),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LoopType(
                    loopType = type,
                    color = MaterialTheme.colorScheme.onSecondary,
                    size = 16.dp
                )
            }
        }
    }
}

@Composable
fun LoopTypeDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    extraContent: @Composable () -> Unit
) = DropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismissRequest,
    modifier = Modifier
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 3.dp),
    offset = DpOffset(
        x = 0.dp,
        y = 12.dp
    )
) {
    extraContent()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Loop.LoopType.entries.forEach { type ->
            Row(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (type) {
                        Front -> stringResource(R.string.knit_stitch)
                        Back -> stringResource(R.string.purl_stitch)
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = stringResource(R.string.space_is_space),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LoopType(
                    loopType = type,
                    color = MaterialTheme.colorScheme.onSecondary,
                    size = 16.dp
                )
            }
        }
    }
}