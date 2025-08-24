package com.njpg.emp.core

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    val background: String/* = "#222222"*/,
    val hovered: String/* = "#444444"*/,
    val pressed: String/* = "#555555"*/,
    val yellow: String/* = "#ffc600"*/,
    val yellowToggled: String/* = "#cca000"*/,
    val red: String/* = "#FB5012"*/,
    val redPressed: String/* = "#FC7A4A"*/,
    val white: String/* = "#f4fff8"*/
) {
    @kotlinx.serialization.Transient
    val transparent: Color = Color.Transparent
}
