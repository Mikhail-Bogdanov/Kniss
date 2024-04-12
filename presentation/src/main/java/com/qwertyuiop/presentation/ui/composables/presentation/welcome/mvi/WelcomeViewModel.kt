package com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi

import androidx.appcompat.app.AppCompatDelegate.setApplicationLocales
import androidx.core.os.LocaleListCompat.forLanguageTags
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.ContinueClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.NextTipClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class WelcomeViewModel : MviViewModel<WelcomeState, WelcomeSideEffect, WelcomeEvent>(
    initialState = WelcomeState()
) {
    override fun dispatch(event: WelcomeEvent) {
        when (event) {
            ContinueClicked -> continueClicked()
            is NextTipClicked -> nextTipClicked(event.contentToRemove)
            is WelcomeEvent.LanguageSelected -> languageSelected(event.language)
        }
    }

    private fun languageSelected(language: Language) = intent {
        withContext(Dispatchers.Main) {
            setApplicationLocales(forLanguageTags(language.tag))
        }
        reduce {
            state.copy(
                remainingGreetingContent = state.remainingGreetingContent.filterNot {
                    it == GreetingContent.Language
                }
            )
        }
    }

    private fun continueClicked() = intent {
        postSideEffect(NavigateToStart)
    }

    private fun nextTipClicked(greetingContent: GreetingContent) = intent {
        reduce {
            state.copy(
                remainingGreetingContent = state.remainingGreetingContent.filterNot {
                    it == greetingContent
                }
            )
        }
    }
}