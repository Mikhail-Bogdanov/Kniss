package com.ex.remotedata.network

import com.ex.remotedata.dtos.AnswerDto
import com.ex.remotedata.dtos.AuthDto
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
