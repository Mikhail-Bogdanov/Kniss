package com.qwertyuiop.presentation.ui.utils.extensions

import com.qwertyuiop.domain.entities.Loop
import com.qwertyuiop.presentation.ui.composables.destinations.DirectionDestination
import com.qwertyuiop.presentation.ui.composables.presentation.shared.LoopDTO
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

fun String.isBlankOrEmpty() = isBlank() || isEmpty()

fun <T> List<T>.extend(t: T) = toMutableList().apply {
    add(t)
}

fun <T> List<List<T>>.dropInnerLast(index: Int) = toMutableList().apply {
    this[index].dropLast(1)
}

fun <T> List<List<T>>.extendInner(index: Int, t: T) = toMutableList().apply {
    this[index] = this[index].toMutableList().apply { add(t) }
}

fun List<List<Loop>>.updateInnerItem(l: Loop, onFound: () -> Loop) = toMutableList().apply {
    replaceAll { list ->
        if (list.contains(l))
            list.toMutableList().apply {
                replaceAll { loop ->
                    if (loop === l)
                        onFound()
                    else
                        loop
                }
            }
        else
            list
    }
}

fun List<List<Loop>>.removeWherever(l: Loop) = toMutableList().apply {
    replaceAll { row ->
        if (row.contains(l))
            row.filterNot { it === l }
        else
            row
    }
}.filter { it.isNotEmpty() }

fun DestinationsNavigator.navigateClear(dest: DirectionDestination) {
    navigate(dest) {
        popUpTo(popUpToRoute ?: "root")
    }
}

fun Loop.toLoopDTO() = LoopDTO(type = type)