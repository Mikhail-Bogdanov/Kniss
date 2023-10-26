package com.example.gray.ui.gray.mvi

import androidx.annotation.Keep

@Keep
data class GrayState(
    val isLoading: Boolean = true,
    val isGranted: Boolean = false,
    val url: String? = null
)
