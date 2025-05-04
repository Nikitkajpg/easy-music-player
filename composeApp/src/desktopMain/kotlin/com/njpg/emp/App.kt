package com.njpg.emp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.ui.components.TopPanel
import com.njpg.emp.ui.util.AppColors

@Composable
fun WindowScope.App(windowState: WindowState) {
    Column(
        modifier = Modifier.fillMaxSize().background(AppColors.background)
    ) {
        TopPanel(windowState)
//        MiddlePanel()
//        BottomPanel()
    }
}