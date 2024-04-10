package com.qwertyuiop.presentation.ui.composables.presentation.shared.loopType

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.qwertyuiop.domain.entities.Loop

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
fun bigCircleState() = BigCircleState(
    radius = with(LocalDensity.current) {
        10.dp.toPx()
    },
    center = with(LocalDensity.current) {
        Offset(10.dp.toPx(), 10.dp.toPx())
    },
    width = with(LocalDensity.current) {
        2.dp.toPx()
    }
)

@Composable
fun smallCircleState(loopType: Loop.LoopType) = SmallCircleState(
    radius = animateFloatAsState(
        targetValue = with(LocalDensity.current) {
            when (loopType) {
                com.qwertyuiop.domain.entities.Loop.LoopType.Front -> 6.dp.toPx()
                com.qwertyuiop.domain.entities.Loop.LoopType.Back -> 0.dp.toPx()
            }
        }, label = ""
    ).value,
    center = with(LocalDensity.current) {
        Offset(10.dp.toPx(), 10.dp.toPx())
    }
)