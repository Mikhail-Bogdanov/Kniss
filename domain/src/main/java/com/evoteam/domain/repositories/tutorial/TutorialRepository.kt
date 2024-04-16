package com.evoteam.domain.repositories.tutorial

interface TutorialRepository {
    suspend fun getTutorialEnded(): Boolean

    suspend fun setTutorialEnded()
}