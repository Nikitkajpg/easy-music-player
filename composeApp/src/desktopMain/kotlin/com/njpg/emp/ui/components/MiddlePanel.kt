package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun MiddlePanel() {
    Box(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)
    ) {
        CardContainer()
    }
}