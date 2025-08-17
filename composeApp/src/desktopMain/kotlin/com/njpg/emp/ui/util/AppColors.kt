package com.njpg.emp.ui.util

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * Менеджер текущей темы приложения.
 *
 * Позволяет переключаться между светлой и тёмной темами.
 */
object AppColors {
    var current by mutableStateOf<ThemeColors>(DarkThemeColors)

    fun toggleTheme() {
        current = if (current == DarkThemeColors) LightThemeColors else DarkThemeColors
    }
}

/**
 * Преобразует строку в формате HEX (например, "#ff0000") в объект [Color].
 *
 * Автоматически добавляет альфа-канал ff (полная непрозрачность), если он не указан.
 *
 * @receiver [String] строка с цветом в HEX-формате.
 * @return [Color] объект цвета.
 */
fun String.toColor(): Color {
    val hex = removePrefix("#").lowercase()
    return Color("ff$hex".toLong(16).toInt())
}

/**
 * Возвращает текущие цвета приложения с анимацией при смене темы.
 *
 * Используется в Composable функциях для плавного перехода между светлой и тёмной темами.
 *
 * @return [ThemeColors] объект с анимированными цветами.
 */
@Composable
fun animatedAppColors(): ThemeColors {
    val current = AppColors.current
    return object : ThemeColors {
        override val background by animateColorAsState(current.background)
        override val hovered by animateColorAsState(current.hovered)
        override val pressed by animateColorAsState(current.pressed)
        override val yellow by animateColorAsState(current.yellow)
        override val yellowToggled by animateColorAsState(current.yellowToggled)
        override val red by animateColorAsState(current.red)
        override val redPressed by animateColorAsState(current.redPressed)
        override val white by animateColorAsState(current.white)
        override val transparent by animateColorAsState(current.transparent)
    }
}

interface ThemeColors {
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

object DarkThemeColors : ThemeColors {
    override val background = "#222222".toColor()
    override val hovered = "#444444".toColor()
    override val pressed = "#555555".toColor()
    override val yellow = "#ffc600".toColor()
    override val yellowToggled = "#cca000".toColor()
    override val red = "#FB5012".toColor()
    override val redPressed = "#FC7A4A".toColor()
    override val white = "#f4fff8".toColor()
    override val transparent = Color.Transparent
}

object LightThemeColors : ThemeColors {
    override val background = "#ffffff".toColor()
    override val hovered = "#dddddd".toColor()
    override val pressed = "#cccccc".toColor()
    override val yellow = "#ffc600".toColor()
    override val yellowToggled = "#cca000".toColor()
    override val red = "#FB5012".toColor()
    override val redPressed = "#FC7A4A".toColor()
    override val white = "#222222".toColor()
    override val transparent = Color.Transparent
}