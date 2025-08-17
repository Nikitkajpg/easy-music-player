package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.njpg.emp.core.CardManager
import com.njpg.emp.core.CardPosition
import com.njpg.emp.ui.util.AppColors
import kotlin.math.roundToInt

@Composable
fun DraggableCard(
    id: String,
    modifier: Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    val saved = CardManager.positions.find { it.id == id }
    var offsetX by remember { mutableStateOf(saved?.x ?: 0f) }
    var offsetY by remember { mutableStateOf(saved?.y ?: 0f) }

    Column(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
            .background(AppColors.hovered)
            .border(1.dp, AppColors.red)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .background(AppColors.pressed)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()

                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                            if (offsetY < 0)
                                offsetY = 0f

                            CardManager.updatePosition(CardPosition(id, offsetX, offsetY))
                            println("${offsetX.roundToInt()}, ${offsetY.roundToInt()}")
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text("Drag me", color = AppColors.white)
        }

        Box(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}