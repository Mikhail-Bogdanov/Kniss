package com.qwertyuiop.remotedata.network

import com.qwertyuiop.remotedata.dtos.AnswerDto
import com.qwertyuiop.remotedata.dtos.AuthDto
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
