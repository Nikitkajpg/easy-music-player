package com.njpg.emp.data

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.njpg.emp.core.Theme

object ThemeManager {
    var current by mutableStateOf(DarkTheme)

    fun setTheme(theme: Theme) {
        current = theme
    }

    fun toggleTheme() {
        current = if (current == DarkTheme) LightTheme else DarkTheme
    }

    fun getThemeAsString(): Theme = current
}

fun String.toColor(): Color {
    val hex = removePrefix("#").lowercase()
    return Color("ff$hex".toLong(16).toInt())
}

@Composable
fun animatedAppColors(): ThemeColorsComposable {
    val current = ThemeManager.current
    return object : ThemeColorsComposable {
        override val background by animateColorAsState(current.background.toColor())
        override val hovered by animateColorAsState(current.hovered.toColor())
        override val pressed by animateColorAsState(current.pressed.toColor())
        override val yellow by animateColorAsState(current.yellow.toColor())
        override val yellowToggled by animateColorAsState(current.yellowToggled.toColor())
        override val red by animateColorAsState(current.red.toColor())
        override val redPressed by animateColorAsState(current.redPressed.toColor())
        override val white by animateColorAsState(current.white.toColor())
        override val transparent by animateColorAsState(current.transparent)
    }
}

interface ThemeColorsComposable {
    val background: Color
    val hovered: Color
    val pressed: Color
    val yellow: Color
    val yellowToggled: Color
    val red: Color
    val redPressed: Color
    val white: Color
    val transparent: Color
}

val DarkTheme = Theme (
    background = "#222222",
    hovered = "#444444",
    pressed = "#555555",
    yellow = "#ffc600",
    yellowToggled = "#cca000",
    red = "#FB5012",
    redPressed = "#FC7A4A",
    white = "#f4fff8"
)

val LightTheme = Theme (
    background = "#ffffff",
    hovered = "#dddddd",
    pressed = "#cccccc",
    yellow = "#ffc600",
    yellowToggled = "#cca000",
    red = "#FB5012",
    redPressed = "#FC7A4A",
    white = "#222222"
)