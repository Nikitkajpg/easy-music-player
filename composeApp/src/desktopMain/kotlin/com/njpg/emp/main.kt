package com.njpg.emp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.icon_svg
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val windowState = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Easy Music Player",
        icon = painterResource(Res.drawable.icon_svg),
        undecorated = true,
        transparent = true,
        state = windowState
    ) {
        App(windowState)
    }
}
