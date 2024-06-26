package com.evoteam.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.evoteam.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeNextButton
import com.evoteam.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.evoteam.presentation.ui.utils.composables.PrimaryInputField

@Composable
fun PropertiesContent(onEvent: (WelcomeEvent) -> Unit) {
    WelcomeScreenText(text = stringResource(R.string.choose_properties))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryInputField(
            value = "",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = MaterialTheme.shapes.medium
        )
        PrimaryInputField(
            value = "",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.width),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = MaterialTheme.shapes.medium
        )
        PrimaryInputField(
            value = "",
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.height),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            shape = MaterialTheme.shapes.medium
        )
    }
    WelcomeNextButton(onEvent = onEvent)
}