package com.qwertyuiop.domain.entities

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