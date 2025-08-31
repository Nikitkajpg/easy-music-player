package com.njpg.emp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.njpg.emp.data.*
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.icon_svg
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val windowState = rememberWindowState()
    var isInitialized by mutableStateOf(false)

    LaunchedEffect(Unit) {
        val config = ConfigStorage.load()
        CardManager.init()
        Localization.init(config)
        ThemeManager.setTheme(config.theme)
        isInitialized = true
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Easy Music Player",
        icon = painterResource(Res.drawable.icon_svg),
        undecorated = true,
        transparent = true,
        state = windowState
    ) {
        if (isInitialized) {
            App(windowState)
        }
    }
}
