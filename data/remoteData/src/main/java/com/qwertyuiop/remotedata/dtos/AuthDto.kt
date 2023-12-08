package com.qwertyuiop.remotedata.dtos

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthDto(
    @SerializedName("us")
    val usbCharge: Boolean,
    @SerializedName("dv")
    val device: String,
    @SerializedName("pg")
    val page: String
)
