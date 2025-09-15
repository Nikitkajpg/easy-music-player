package com.njpg.emp.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.njpg.emp.ui.util.toColor

object ThemeManager {
    var currentTheme by mutableStateOf(DarkTheme)
        private set

    fun setTheme(theme: Theme) {
        currentTheme = theme
    }

    fun toggleTheme() {
        currentTheme = if (currentTheme == DarkTheme) LightTheme else DarkTheme
    }
}

@Immutable
data class ThemeColors(
    val background: Color,
    val hovered: Color,
    val pressed: Color,
    val text: Color,
    val toggled: Color,
    val red: Color,
    val redPressed: Color,
    val icon: Color,
    val transparent: Color
)

object AppTheme {
    val colors: ThemeColors
        @Composable
        get() {
            val current = ThemeManager.currentTheme
            return ThemeColors(
                background = animateColorAsState(current.background.toColor()).value,
                hovered = animateColorAsState(current.hovered.toColor()).value,
                pressed = animateColorAsState(current.pressed.toColor()).value,
                text = animateColorAsState(current.text.toColor()).value,
                toggled = animateColorAsState(current.toggled.toColor()).value,
                red = animateColorAsState(current.red.toColor()).value,
                redPressed = animateColorAsState(current.redPressed.toColor()).value,
                icon = animateColorAsState(current.icon.toColor()).value,
                transparent = Color.Transparent
            )
        }
}