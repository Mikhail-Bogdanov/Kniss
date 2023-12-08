package com.qwertyuiop.white.ui.composables.settings.mvi

import androidx.lifecycle.viewModelScope
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domainwhite.useCases.locale.SaveLocaleUseCase
import com.qwertyuiop.domainwhite.useCases.theme.SaveThemeUseCase
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.BackButtonClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.HideLocaleDialogClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.HideThemeDialogClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.LocaleClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.PolicyClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.RateUsClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.ShowLocaleDialogClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.ShowThemeDialogClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.TermsClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.ThemeClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsEvent.WriteUsClicked
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenMail
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPlayMarket
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenPolicy
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.OpenTerms
import com.qwertyuiop.white.ui.composables.settings.mvi.SettingsSideEffect.PopBackStack
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class SettingsViewModel(
    private val saveLocaleUseCase: SaveLocaleUseCase,
    private val saveThemeUseCase: SaveThemeUseCase
) : MviViewModel<SettingsState, SettingsSideEffect, SettingsEvent>(
    initialState = SettingsState()
) {
    override fun dispatch(event: SettingsEvent) {
        when (event) {
            BackButtonClicked -> backButtonCLicked()
            is LocaleClicked -> languageClicked(event.locale)
            PolicyClicked -> policyClicked()
            RateUsClicked -> rateUsClicked()
            TermsClicked -> termsClicked()
            is ThemeClicked -> themeClicked(event.darkTheme)
            WriteUsClicked -> writeUsClicked()
            HideLocaleDialogClicked -> hideLocaleDialogClicked()
            HideThemeDialogClicked -> hideThemeDialogClicked()
            ShowLocaleDialogClicked -> showLocaleDialogClicked()
            ShowThemeDialogClicked -> showThemeDialogClicked()
        }
    }

    private fun hideLocaleDialogClicked() = intent {
        reduce { state.copy(localeDialogShowing = false) }
    }

    private fun hideThemeDialogClicked() = intent {
        reduce { state.copy(themeDialogShowing = false) }
    }

    private fun showLocaleDialogClicked() = intent {
        reduce { state.copy(localeDialogShowing = true) }
    }

    private fun showThemeDialogClicked() = intent {
        reduce { state.copy(themeDialogShowing = true) }
    }

    private fun backButtonCLicked() = intent {
        postSideEffect(PopBackStack)
    }

    private fun languageClicked(locale: String) = intent {
        reduce { state.copy(localeDialogShowing = false) }
        viewModelScope.launch {
            saveLocaleUseCase(locale)
        }
    }

    private fun policyClicked() = intent {
        postSideEffect(OpenPolicy)
    }

    private fun rateUsClicked() = intent {
        postSideEffect(OpenPlayMarket)
    }

    private fun termsClicked() = intent {
        postSideEffect(OpenTerms)
    }

    private fun themeClicked(darkTheme: Boolean) = intent {
        reduce { state.copy(themeDialogShowing = false) }
        viewModelScope.launch {
            saveThemeUseCase(darkTheme)
        }
    }

    private fun writeUsClicked() = intent {
        postSideEffect(OpenMail)
    }
}