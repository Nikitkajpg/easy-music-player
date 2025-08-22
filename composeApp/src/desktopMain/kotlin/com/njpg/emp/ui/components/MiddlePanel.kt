package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.animatedAppColors

/**
 * Центральная панель приложения, содержащая интерактивные карточки.
 *
 * Панель занимает всё доступное пространство и использует текущий цвет фона
 * из [animatedAppColors] для адаптации к светлой или тёмной теме.
 */
@Composable
fun MiddlePanel(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize().background(animatedAppColors().background)
    ) {
        DraggableCard(
            "Card 1", modifier = Modifier.size(200.dp, 200.dp)
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
            "Card 2", modifier = Modifier.size(200.dp, 200.dp)
        ) {
            Text("Это просто текст в другой карточке!", modifier = Modifier.align(Alignment.Center))
        }
    }
}