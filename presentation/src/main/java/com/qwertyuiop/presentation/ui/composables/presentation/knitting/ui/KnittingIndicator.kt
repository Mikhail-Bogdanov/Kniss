package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingState
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KnittingIndicator(
    state: KnittingState
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

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(horizontalScrollState)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
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

                        KnittingLoopItem(
                            loop = state.loops[reversedRowIndex][reversedLoopIndex],
                            isCurrentRow = state.currentRow == reversedRowIndex,
                            rowIndex = reversedRowIndex,
                            loopIndex = reversedLoopIndex
                        )
                    }
                }
            }
        }
    }
}