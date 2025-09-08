package com.njpg.emp.ui.util

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    val hex = removePrefix("#").lowercase()
    return Color("ff$hex".toLong(16).toInt())
}