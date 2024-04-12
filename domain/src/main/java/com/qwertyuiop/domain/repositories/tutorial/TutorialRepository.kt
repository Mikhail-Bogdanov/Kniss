package com.qwertyuiop.domain.repositories.tutorial

interface TutorialRepository {
    suspend fun getTutorialEnded(): Boolean

    suspend fun setTutorialEnded()
}