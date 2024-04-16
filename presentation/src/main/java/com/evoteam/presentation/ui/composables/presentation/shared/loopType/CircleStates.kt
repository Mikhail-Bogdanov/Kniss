package com.evoteam.presentation.ui.composables.presentation.shared.loopType

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evoteam.domain.entities.Loop.LoopType

data class BigCircleState(
    val radius: Float,
    val center: Offset,
    val width: Float
)

data class SmallCircleState(
    val radius: Float,
    val center: Offset
)

@Composable
fun bigCircleState(indicatorSize: Dp) = BigCircleState(
    radius = with(LocalDensity.current) {
        indicatorSize.value.div(2).dp.toPx()
    },
    center = with(LocalDensity.current) {
        Offset(
            indicatorSize.value.div(2).dp.toPx(),
            indicatorSize.value.div(2).dp.toPx()
        )
    },
    width = with(LocalDensity.current) {
        indicatorSize.value.div(10).dp.toPx()
    }
)

@Composable
fun smallCircleState(loopType: LoopType, indicatorSize: Dp) = SmallCircleState(
    radius = animateFloatAsState(
        targetValue = with(LocalDensity.current) {
            when (loopType) {
                LoopType.Front -> indicatorSize.value.div(3.5f).dp.toPx()
                LoopType.Back -> 0.dp.toPx()
            }
        }, label = ""
    ).value,
    center = with(LocalDensity.current) {
        Offset(
            indicatorSize.value.div(2).dp.toPx(),
            indicatorSize.value.div(2).dp.toPx()
        )
    }
)