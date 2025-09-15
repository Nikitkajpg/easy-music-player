package com.njpg.emp.ui.theme

import kotlinx.serialization.Serializable

val DarkTheme = Theme(
    background = "#222222",
    hovered = "#444444",
    pressed = "#555555",
    text = "#f4fff8",
    toggled = "#cca000", //todo поменять потом (или нет)
    red = "#FB5012",
    redPressed = "#FC7A4A",
    icon = "#f4fff8"
)

val LightTheme = Theme(
    background = "#f4fff8",
    hovered = "#dddddd",
    pressed = "#cccccc",
    text = "#222222",
    toggled = "#cca000", //todo поменять потом (или нет)
    red = "#FB5012",
    redPressed = "#FC7A4A",
    icon = "#222222"
)

@Serializable
data class Theme(
    val background: String/* = "#222222"*/,
    val hovered: String/* = "#444444"*/,
    val pressed: String/* = "#555555"*/,
    val text: String/* = "#ffc600"*/,
    val toggled: String/* = "#cca000"*/,
    val red: String/* = "#FB5012"*/,
    val redPressed: String/* = "#FC7A4A"*/,
    val icon: String/* = "#f4fff8"*/
)