// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.App

fun main() = application {
    val state = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Easy Music Player",
        icon = painterResource("icons/emp_png48.png"),
        undecorated = true,
        resizable = false,
        state = state
    ) {
        App(state)
    }
}

