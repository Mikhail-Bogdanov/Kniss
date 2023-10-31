package com.ex.remotedata.dtos


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class AnswerDto(
    @SerializedName("status")
    val answer: String
)
