package com.qwertyuiop.domain.useCases.tutorial

import com.qwertyuiop.domain.repositories.tutorial.TutorialRepository

class EndTutorialUseCase(
    private val tutorialRepository: TutorialRepository
) {
    suspend operator fun invoke() = tutorialRepository.setTutorialEnded()
}