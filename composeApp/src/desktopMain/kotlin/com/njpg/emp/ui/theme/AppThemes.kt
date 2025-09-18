package com.njpg.emp.ui.theme

import kotlinx.serialization.Serializable

val DarkTheme = Theme(
    background = "#222222",     // dark
    hovered = "#444444",        // not so dark
    pressed = "#555555",        // not so dark 2x
    text = "#f4fff8",           // white
    toggled = "#cca000",        // dark yellow todo поменять потом (или нет)
    red = "#fb5012",            // red
    redPressed = "#fc7a4a",     // light red
    icon = "#f4fff8"            // white
)

val LightTheme = Theme(
    background = "#f4fff8",     // white
    hovered = "#dddddd",        // light 2x
    pressed = "#cccccc",        // light
    text = "#222222",           // dark
    toggled = "#cca000",        // dark yellow todo поменять потом (или нет)
    red = "#fb5012",            // red
    redPressed = "#fc7a4a",     // light red
    icon = "#222222"            // dark
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