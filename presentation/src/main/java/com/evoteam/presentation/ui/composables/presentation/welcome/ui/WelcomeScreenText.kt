package com.evoteam.presentation.ui.composables.presentation.welcome.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreenText(text: String, minLines: Int = 1) = Text(
    text = text,
    style = MaterialTheme.typography.bodyLarge,
    color = MaterialTheme.colorScheme.onBackground,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 48.dp),
    textAlign = TextAlign.Center,
    minLines = minLines
)