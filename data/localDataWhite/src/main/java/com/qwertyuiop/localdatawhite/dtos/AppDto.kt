package com.qwertyuiop.localdatawhite.dtos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "appTable")
data class AppDto(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String
)
