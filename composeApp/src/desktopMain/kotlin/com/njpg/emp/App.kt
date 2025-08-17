package com.njpg.emp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.ui.components.MiddlePanel
import com.njpg.emp.ui.components.TopPanel

@Composable
fun WindowScope.App(windowState: WindowState) {
    Column {
        TopPanel(windowState)
        MiddlePanel()
    }
}