package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.CardManager
import com.njpg.emp.data.animatedAppColors

@Composable
fun MiddlePanel() {
    Box(
        modifier = Modifier.fillMaxSize().background(animatedAppColors().background)
    ) {
        CardContainer()
    }
}