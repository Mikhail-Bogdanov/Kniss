package com.qwertyuiop.presentation.ui.composables.presentation.shared

import com.qwertyuiop.domain.entities.Loop
import kotlinx.serialization.Serializable

@Serializable
data class KnittingPatternState(
    val width: Int,
    val height: Int,
    val pattern: List<List<LoopDTO>>
)

@Serializable
data class LoopDTO(
    val type: Loop.LoopType
) {
    fun toLoop() = Loop(type = type)
}
