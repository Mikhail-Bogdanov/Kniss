package com.evoteam.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent.LoopItemClicked
import com.evoteam.presentation.ui.composables.presentation.knitting.mvi.KnittingState
import com.evoteam.presentation.ui.utils.composables.ComposableConstants
import com.evoteam.presentation.ui.utils.composables.HorizontalScrollBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KnittingIndicator(
    state: KnittingState,
    onEvent: (KnittingEvent) -> Unit
) {
    val horizontalScrollState = rememberScrollState()
    val verticalScrollState = rememberLazyListState()

    LaunchedEffect(state.loops.size) {
        horizontalScrollState.scrollTo(horizontalScrollState.maxValue)
        if (state.loops.isNotEmpty()) verticalScrollState.scrollToItem(state.loops.lastIndex)
    }

    LaunchedEffect(state.currentRow) {
        launch {
            horizontalScrollState.animateScrollTo(horizontalScrollState.maxValue)
        }
        if (state.loops.isNotEmpty() && verticalScrollState.canScrollForward)
            verticalScrollState.animateScrollToItem(state.loops.lastIndex)
    }

    HorizontalScrollBox(
        horizontalScrollState = horizontalScrollState,
        alignment = Alignment.BottomEnd
    ) {
        LazyColumn(
            modifier = Modifier
                .defaultMinSize(
                    minWidth = ComposableConstants.ScreenWidth
                        .minus(24.dp) //paddings
                        .minus(4.dp) //paddings
                )
                .fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.End,
            state = verticalScrollState
        ) {
            items(
                count = state.loops.size,
                key = { it }
            ) { rowIndex ->
                val reversedRowIndex = state.loops.size.minus(1).minus(rowIndex)

                if (reversedRowIndex >= state.currentRow) Row(
                    modifier = Modifier.animateItemPlacement(),
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(state.loops[reversedRowIndex].size) { loopIndex ->
                        val reversedLoopIndex = state.loops[reversedRowIndex].size
                            .minus(1)
                            .minus(loopIndex)

                        val loop = state.loops[reversedRowIndex][reversedLoopIndex]

                        KnittingLoopItem(
                            loopType = loop.type,
                            isCurrentRow = state.currentRow == reversedRowIndex,
                            rowIndex = reversedRowIndex,
                            loopIndex = reversedLoopIndex,
                            clickEnabled = state.isInEdit
                        ) {
                            onEvent(LoopItemClicked(loop))
                        }
                    }
                }
            }
        }
    }
}