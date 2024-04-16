package com.evoteam.presentation.ui.utils.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evoteam.domain.entities.Loop
import com.evoteam.presentation.ui.composables.destinations.DirectionDestination
import com.evoteam.presentation.ui.utils.composables.ComposableConstants
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
        popUpTo("root") {
            inclusive = true
        }
    }
}


@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.fillScreenWidth(
    fraction: Float = 1f
) = width(ComposableConstants.ScreenWidth.times(fraction))

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.fillScreenHeight(
    fraction: Float = 1f
) = height(ComposableConstants.ScreenHeight.times(fraction))