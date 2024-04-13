package com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.greetingContentUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evoteam.presentation.R
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.LanguageSelected
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.ui.WelcomeScreenText
import com.qwertyuiop.presentation.ui.utils.composables.PrimaryButton
import com.qwertyuiop.presentation.ui.utils.extensions.fillScreenHeight

@Composable
fun LanguageContent(onEvent: (WelcomeEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillScreenHeight(0.6f),
        verticalArrangement = Arrangement.spacedBy(
            24.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeScreenText(text = stringResource(R.string.select_language))
        Language.entries.filter { it != Language.Default }.forEach { language ->
            PrimaryButton(text = language.untranslatableTitle) {
                onEvent(LanguageSelected(language))
            }
        }
    }
}