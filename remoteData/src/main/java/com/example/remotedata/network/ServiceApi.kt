package com.example.remotedata.network

import com.example.remotedata.dtos.AnswerDto
import com.example.remotedata.dtos.AuthDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceApi {

    private companion object {
        const val post = "/"
    }

    @POST(post)
    suspend fun getAnswer(
        @Body authDto: AuthDto
    ): AnswerDto
}
