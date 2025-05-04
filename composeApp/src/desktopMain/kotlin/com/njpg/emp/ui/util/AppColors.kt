package com.njpg.emp.ui.util

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    val hex = removePrefix("#").lowercase()

    return Color("ff$hex".toLong(16).toInt())
}

object AppColors {
    val background = "#222222".toColor()
    val hovered = "#444444".toColor()
    val pressed = "#555555".toColor()
    val yellow = "#ffc600".toColor()
    val red = "#FB5012".toColor()
    val redPressed = "#FC7A4A".toColor()
    val white = "#f4fff8".toColor()
    val transparent = Color.Transparent
}