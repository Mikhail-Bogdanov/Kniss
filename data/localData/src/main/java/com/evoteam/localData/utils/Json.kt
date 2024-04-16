package com.evoteam.localData.utils

import kotlinx.serialization.json.Json

val DataJson = Json {
    encodeDefaults = true
    allowTrailingComma = true
}