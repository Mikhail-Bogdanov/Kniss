package com.evoteam.presentation.ui.utils.composables

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

@Composable
fun PrimaryInputField(
    value: Any,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    isError: Boolean = false,
    shape: Shape = TextFieldDefaults.shape,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) = TextField(
    value = value.toString(),
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    textStyle = textStyle,
    singleLine = singleLine,
    maxLines = maxLines,
    minLines = minLines,
    isError = isError,
    shape = shape,
    keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,
    colors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledTextColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        errorTextColor = MaterialTheme.colorScheme.error,
        focusedContainerColor = MaterialTheme.colorScheme.primary,
        unfocusedContainerColor = MaterialTheme.colorScheme.primary.copy(0.75f),
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(0.75f),
        errorContainerColor = MaterialTheme.colorScheme.primary.copy(0.75f),
        cursorColor = MaterialTheme.colorScheme.onPrimary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
        disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        errorLeadingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledTrailingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        focusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        errorTrailingIconColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        focusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        errorSupportingTextColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledSupportingTextColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
        disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimary.copy(0.25f),
        errorPlaceholderColor = MaterialTheme.colorScheme.error.copy(0.50f),
        focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledLabelColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        errorLabelColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        focusedSuffixColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledSuffixColor = MaterialTheme.colorScheme.onPrimary.copy(0.25f),
        errorSuffixColor = MaterialTheme.colorScheme.error.copy(0.5f),
        unfocusedSuffixColor = MaterialTheme.colorScheme.onPrimary.copy(0.5f),
        focusedPrefixColor = MaterialTheme.colorScheme.onPrimary.copy(0.75f),
        disabledPrefixColor = MaterialTheme.colorScheme.onPrimary.copy(0.25f),
        errorPrefixColor = MaterialTheme.colorScheme.error.copy(0.5f),
        unfocusedPrefixColor = MaterialTheme.colorScheme.onPrimary.copy(0.5f)
    ),
    label = label,
    placeholder = placeholder,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    prefix = prefix,
    suffix = suffix,
    supportingText = supportingText,
)