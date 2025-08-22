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
import com.njpg.emp.core.CardPosition
import com.njpg.emp.data.CardManager
import com.njpg.emp.data.Localization
import com.njpg.emp.data.animatedAppColors
import kotlin.math.roundToInt

/**
 * Перетаскиваемая карточка с произвольным содержимым.
 *
 * Карточка запоминает своё положение через [CardManager] и позволяет пользователю
 * изменять позицию по экрану с помощью мыши.
 */
@Composable
fun DraggableCard(
    id: String, modifier: Modifier = Modifier, content: @Composable (BoxScope.() -> Unit)
) {
    val saved = remember { CardManager.positions.find { it.id == id } }
    var offsetX by remember { mutableStateOf(saved?.x ?: 0f) }
    var offsetY by remember { mutableStateOf(saved?.y ?: 0f) }

    fun updatePositions(x: Float, y: Float) {
        offsetX = x
        offsetY = y.coerceAtLeast(0f)
        CardManager.update(CardPosition(id, offsetX, offsetY))
    }

    Column(modifier = modifier.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .background(animatedAppColors().hovered).border(1.dp, animatedAppColors().red)) {
        /**
         * Заголовок для перетаскивания
         */
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp).background(animatedAppColors().pressed)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        updatePositions(offsetX + dragAmount.x, offsetY + dragAmount.y)
                    }
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = Localization.tr("drag"), color = animatedAppColors().white)
        }

        Box(
            modifier = Modifier.padding(16.dp), content = content
        )
    }
}