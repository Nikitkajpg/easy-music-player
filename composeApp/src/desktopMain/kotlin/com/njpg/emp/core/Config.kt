package com.njpg.emp.core

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val lang: String = "en",
    val theme: String = "dark"
)
