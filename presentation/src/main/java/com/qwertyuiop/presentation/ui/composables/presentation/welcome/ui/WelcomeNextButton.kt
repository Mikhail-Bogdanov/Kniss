package com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.NextTipClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton

@Composable
fun WelcomeNextButton(
    greetingContent: GreetingContent,
    onEvent: (WelcomeEvent) -> Unit
) = PrimaryButton(
    text = stringResource(R.string.next),
    modifier = Modifier
        .fillMaxWidth(0.8f)
        .padding(bottom = 72.dp)
) {
    onEvent(NextTipClicked(greetingContent))
}