package com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi

import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent

data class WelcomeState(
    val remainingGreetingContent: List<GreetingContent> = GreetingContent.entries.toList(),
    val isLoading: Boolean = true
)

sealed interface WelcomeEvent {
    data class NextTipClicked(
        val contentToRemove: GreetingContent
    ) : WelcomeEvent

    data object ContinueClicked : WelcomeEvent
    data class LanguageSelected(
        val language: Language
    ) : WelcomeEvent
    data object Initialize : WelcomeEvent
}

sealed interface WelcomeSideEffect {
    data object NavigateToStart : WelcomeSideEffect
}