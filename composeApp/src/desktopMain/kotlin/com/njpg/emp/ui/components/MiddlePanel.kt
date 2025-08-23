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
        DraggableCard(
            "Card 1", modifier = Modifier.size(CardManager.cards[0].width.dp, CardManager.cards[0].height.dp)
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
            "Card 2", modifier = Modifier.size(CardManager.cards[1].width.dp, CardManager.cards[1].height.dp)
        ) {
            Text("Это просто текст в другой карточке!", modifier = Modifier.align(Alignment.Center))
        }
    }
}