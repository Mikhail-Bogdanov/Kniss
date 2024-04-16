package com.evoteam.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Knitting(
    val id: String,
    val width: Int,
    val height: Int,
    val loops: List<List<Loop>>,
    val currentRow: Int,
    val name: String
)
