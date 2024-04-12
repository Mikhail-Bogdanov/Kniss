package com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi

import androidx.appcompat.app.AppCompatDelegate.setApplicationLocales
import androidx.core.os.LocaleListCompat.forLanguageTags
import com.qwertyuiop.core.mviViewModel.MviViewModel
import com.qwertyuiop.domain.useCases.tutorial.EndTutorialUseCase
import com.qwertyuiop.domain.useCases.tutorial.GetTutorialEndedUseCase
import com.qwertyuiop.presentation.ui.composables.presentation.settings.utils.Language
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.ContinueClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.Initialize
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.LanguageSelected
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeEvent.NextTipClicked
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.mvi.WelcomeSideEffect.NavigateToStart
import com.qwertyuiop.presentation.ui.composables.presentation.welcome.utils.GreetingContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class WelcomeViewModel(
    private val endTutorialUseCase: EndTutorialUseCase,
    private val getTutorialEndedUseCase: GetTutorialEndedUseCase
) : MviViewModel<WelcomeState, WelcomeSideEffect, WelcomeEvent>(
    initialState = WelcomeState()
) {
    init {
        dispatch(Initialize)
    }

    override fun dispatch(event: WelcomeEvent) {
        when (event) {
            ContinueClicked -> continueClicked()
            is NextTipClicked -> nextTipClicked(event.contentToRemove)
            is LanguageSelected -> languageSelected(event.language)
            Initialize -> initialize()
        }
    }

    private fun initialize() = intent {
        if (getTutorialEndedUseCase())
            postSideEffect(NavigateToStart)
        else
            reduce { state.copy(isLoading = false) }
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
        endTutorialUseCase()
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