package com.njpg.emp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Easy Music Player",
    ) {
        App()
    }
}