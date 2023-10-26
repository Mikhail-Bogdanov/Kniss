package com.example.white.ui.composables.start.mvi

import androidx.annotation.Keep

@Keep
data class StartState(
    val time: Long = System.currentTimeMillis()
)
