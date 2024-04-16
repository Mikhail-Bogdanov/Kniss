package com.evoteam.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.evoteam.domain.entities.Loop
import com.evoteam.presentation.ui.utils.composables.LoopType
import com.evoteam.presentation.ui.utils.composables.ComposableConstants.LoopItemSize

@Composable
fun KnittingLoopItem(
    loopType: Loop.LoopType,
    isCurrentRow: Boolean,
    rowIndex: Int,
    loopIndex: Int,
    clickEnabled: Boolean,
    onClick: () -> Unit
) = Column(
    modifier = Modifier
        .size(LoopItemSize)
        .clip(MaterialTheme.shapes.small)
        .background(
            color = when (isCurrentRow) {
                true -> MaterialTheme.colorScheme.primary
                false -> MaterialTheme.colorScheme.secondary
            }
        )
        .clickable(enabled = clickEnabled, onClick = onClick),
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
    LoopType(
        loopType = loopType,
        color = when (isCurrentRow) {
            true -> MaterialTheme.colorScheme.onPrimary
            false -> MaterialTheme.colorScheme.onSecondary
        },
        size = 12.dp
    )
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