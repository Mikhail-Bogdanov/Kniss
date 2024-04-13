package com.qwertyuiop.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Loop(
    val type: LoopType = LoopType.Front
) {
    enum class LoopType {
        Front, Back
    }

    fun swapType() = when (type) {
        LoopType.Front -> LoopType.Back
        LoopType.Back -> LoopType.Front
    }
}