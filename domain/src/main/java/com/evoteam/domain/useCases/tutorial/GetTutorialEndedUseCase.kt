package com.evoteam.domain.useCases.tutorial

import com.evoteam.domain.repositories.tutorial.TutorialRepository

class GetTutorialEndedUseCase(
    private val tutorialRepository: TutorialRepository
) {
    suspend operator fun invoke() = tutorialRepository.getTutorialEnded()
}