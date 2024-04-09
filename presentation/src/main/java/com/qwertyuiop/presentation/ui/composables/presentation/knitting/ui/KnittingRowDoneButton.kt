package com.qwertyuiop.presentation.ui.composables.presentation.knitting.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingEvent
import com.qwertyuiop.presentation.ui.composables.presentation.knitting.mvi.KnittingState
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton

@Composable
fun KnittingRowDoneButton(
    state: KnittingState,
    onEvent: (KnittingEvent) -> Unit
) = when (state.currentRow) {

    state.loops.size.minus(1) -> {
        val infinityTransaction = rememberInfiniteTransition(label = "")

        val containerColor by infinityTransaction.animateColor(
            initialValue = MaterialTheme.colorScheme.primary,
            targetValue = MaterialTheme.colorScheme.inversePrimary,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        val scale by infinityTransaction.animateFloat(
            initialValue = 1f,
            targetValue = 1.3f,
            animationSpec = infiniteRepeatable(
                animation = tween(750, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )

        Button(
            onClick = { onEvent(KnittingEvent.RowDoneButtonClicked) },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = containerColor
            ),
            modifier = Modifier
                .scale(scale)
        ) {
            Text(
                text = stringResource(R.string.row_done),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

    else -> PrimaryButton(
        text = stringResource(R.string.row_done),
        enabled = state.currentRow != state.loops.size
    ) {
        onEvent(KnittingEvent.RowDoneButtonClicked)
    }
}