package com.qwertyuiop.presentation.ui.composables.presentation.start.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryInputField

@Composable
fun StartScreenTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) = PrimaryInputField(
    value = value,
    onValueChange = onValueChange,
    modifier = Modifier
        .fillMaxWidth(),
    shape = MaterialTheme.shapes.medium,
    placeholder = {
        Text(
            text = placeholderText,
            style = MaterialTheme.typography.bodySmall
        )
    },
    singleLine = true,
    textStyle = MaterialTheme.typography.bodyMedium,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions
)