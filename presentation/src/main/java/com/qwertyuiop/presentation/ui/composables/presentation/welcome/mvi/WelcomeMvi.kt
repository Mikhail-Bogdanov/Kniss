package com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi

import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent

data class WelcomeState(
    val isLoading: Boolean = true,
    val currentGreetingContent: GreetingContent = GreetingContent.Language
)

sealed interface WelcomeEvent {
    data object NextTipClicked : WelcomeEvent

    data object ContinueClicked : WelcomeEvent
    data class LanguageSelected(
        val language: Language
    ) : WelcomeEvent

    data object Initialize : WelcomeEvent
}

sealed interface WelcomeSideEffect {
    data object NavigateToStart : WelcomeSideEffect
}