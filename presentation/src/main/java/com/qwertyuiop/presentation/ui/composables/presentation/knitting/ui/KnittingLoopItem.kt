package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.domain.entities.Loop

@Composable
fun KnittingLoopItem(
    loop: Loop,
    isCurrentRow: Boolean,
    rowIndex: Int,
    loopIndex: Int
) = Column(
    modifier = Modifier
        .size(64.dp)
        .background(
            color = when (isCurrentRow) {
                true -> MaterialTheme.colorScheme.primary
                false -> MaterialTheme.colorScheme.secondary
            },
            shape = MaterialTheme.shapes.small
        ),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly
) {

    Text(
        text = stringResource(R.string.row_index, rowIndex.plus(1)),
        style = MaterialTheme.typography.bodySmall,
        color = when (isCurrentRow) {
            true -> MaterialTheme.colorScheme.onPrimary
            false -> MaterialTheme.colorScheme.onSecondary
        },
        textAlign = TextAlign.Center
    )
    repeat(
        when (loop.type) {
            Loop.LoopType.Front -> 1
            Loop.LoopType.Back -> 2
        }
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.75f),
            color = when (isCurrentRow) {
                true -> MaterialTheme.colorScheme.onPrimary
                false -> MaterialTheme.colorScheme.onSecondary
            }
        )
    }
    Text(
        text = stringResource(R.string.loop_index, loopIndex.plus(1)),
        style = MaterialTheme.typography.bodySmall,
        color = when (isCurrentRow) {
            true -> MaterialTheme.colorScheme.onPrimary
            false -> MaterialTheme.colorScheme.onSecondary
        },
        textAlign = TextAlign.Center
    )
}