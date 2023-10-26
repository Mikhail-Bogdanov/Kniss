package com.example.white.ui.composables.main.mvi

import androidx.annotation.Keep

@Keep
data class MainState(
    val time: Long = System.currentTimeMillis()
)