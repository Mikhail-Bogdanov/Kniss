package com.qwertyuiop.remotedata.dtos

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("us")
    val usbCharge: Boolean,
    @SerializedName("dv")
    val device: String,
    @SerializedName("pg")
    val page: String
)
