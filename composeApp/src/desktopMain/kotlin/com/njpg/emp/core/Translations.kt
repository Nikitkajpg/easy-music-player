package com.njpg.emp.core

import kotlinx.serialization.Serializable

@Serializable
data class Translations(
    val en: Map<String, String> = emptyMap(), val ru: Map<String, String> = emptyMap()
)