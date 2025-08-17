package com.njpg.emp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.njpg.emp.data.FolderController
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.icon_svg
import org.jetbrains.compose.resources.painterResource

fun main() = application {
    val windowState = rememberWindowState()

    LaunchedEffect(Unit) {
        FolderController.load()
    }

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
