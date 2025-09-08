package com.njpg.emp.core

import com.njpg.emp.ui.theme.DarkTheme
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val lang: String = "en",
    val theme: Theme = DarkTheme
)
