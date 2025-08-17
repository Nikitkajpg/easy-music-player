package com.njpg.emp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.ui.components.DraggableCard
import com.njpg.emp.ui.components.TopPanel
import com.njpg.emp.ui.util.AppColors

@Composable
fun WindowScope.App(windowState: WindowState) {
    Column(
        modifier = Modifier.background(AppColors.background)
    ) {
        TopPanel(windowState)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.background)
        ) {
            DraggableCard(
                "Card 1",
                modifier = Modifier.size(200.dp, 200.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { println("Кнопка 1 была нажата") }) {
                        Text("Кнопка 1")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { println("Кнопка 2 была нажата") }) {
                        Text("Кнопка 2")
                    }
                }
            }

            DraggableCard(
                "Card 2",
                modifier = Modifier.size(200.dp, 200.dp)
            ) {
                Text("Это просто текст в другой карточке!", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}