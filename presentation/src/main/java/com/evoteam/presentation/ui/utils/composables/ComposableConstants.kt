package com.evoteam.presentation.ui.utils.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

object ComposableConstants {
    val LoopItemSize = 64.dp

    val ScreenWidth
        @Composable
        get() = LocalConfiguration.current.screenWidthDp.dp

    val ScreenHeight
        @Composable
        get() = LocalConfiguration.current.screenHeightDp.dp
}