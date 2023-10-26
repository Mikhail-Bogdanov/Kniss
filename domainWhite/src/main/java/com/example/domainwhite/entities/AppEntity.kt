package com.example.domainwhite.entities

import androidx.annotation.Keep
import java.util.UUID

@Keep
data class AppEntity(
    val id: String = UUID.randomUUID().toString()
)
