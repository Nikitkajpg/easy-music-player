package com.njpg.emp

import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.njpg.emp.data.CardManager
import com.njpg.emp.data.ConfigStorage
import com.njpg.emp.data.Localization
import com.njpg.emp.ui.theme.ThemeManager
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.icon_svg
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val windowState = rememberWindowState(
        size = DpSize(1280.dp, 720.dp)
    )
    var isInitialized by remember { mutableStateOf(false) }

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
        resizable = false,
        transparent = true,
        state = windowState
    ) {
        if (isInitialized) {
            App(windowState)
        }
    }
}
