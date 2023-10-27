package com.example.domainwhite.entities

import java.util.UUID

data class AppEntity(
    val id: String = UUID.randomUUID().toString()
)
