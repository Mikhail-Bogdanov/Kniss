package com.ex.white.ui.composables.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ex.white.R
import com.ex.white.ui.composables.settings.mvi.SettingsEvent
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.PolicyClicked
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.RateUsClicked
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.ShowLocaleDialogClicked
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.ShowThemeDialogClicked
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.TermsClicked
import com.ex.white.ui.composables.settings.mvi.SettingsEvent.WriteUsClicked
import com.ex.white.ui.composables.settings.mvi.SettingsState

@Composable
fun SettingsScreenContent(
    paddingValues: PaddingValues,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocaleDialog(show = state.localeDialogShowing, onEvent = onEvent)

        ThemeDialog(show = state.themeDialogShowing, onEvent = onEvent)

        SettingsItem(
            icon = Default.Star,
            text = stringResource(R.string.rate_us),
            suppText = stringResource(R.string.rate_the_app)
        ) {
            onEvent(RateUsClicked)
        }

        SettingsItem(
            icon = Default.Mail,
            text = stringResource(R.string.write_us),
            suppText = stringResource(R.string.for_questions_and_suggestions)
        ) {
            onEvent(WriteUsClicked)
        }

        SettingsItem(
            icon = Default.Info,
            text = stringResource(id = R.string.terms_of_use),
            suppText = stringResource(R.string.app_usage_rules)
        ) {
            onEvent(TermsClicked)
        }

        SettingsItem(
            icon = Default.Policy,
            text = stringResource(id = R.string.privacy_policy),
            suppText = stringResource(R.string.app_privacy_policy)
        ) {
            onEvent(PolicyClicked)
        }

        SettingsItem(
            icon = Default.Language,
            text = stringResource(R.string.language),
            suppText = stringResource(R.string.change_language)
        ) {
            onEvent(ShowLocaleDialogClicked)
        }

        SettingsItem(
            icon = Default.DarkMode,
            text = stringResource(R.string.theme),
            suppText = stringResource(R.string.change_app_theme)
        ) {
            onEvent(ShowThemeDialogClicked)
        }
    }
}