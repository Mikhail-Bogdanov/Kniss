package com.qwertyuiop.remotedata.dtos


import com.google.gson.annotations.SerializedName


data class AnswerDto(
    @SerializedName("status")
    val answer: String
)
