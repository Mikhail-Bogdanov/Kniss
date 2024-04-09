package com.qwertyuiop.presentation.ui.utils.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true,
    onClick: () -> Unit
) = Button(
    onClick = onClick,
    modifier = modifier,
    shape = shape,
    enabled = enabled,
    colors = ButtonDefaults.buttonColors(
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primary,
        disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
        disabledContentColor = MaterialTheme.colorScheme.onPrimary
    )
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium
    )
}