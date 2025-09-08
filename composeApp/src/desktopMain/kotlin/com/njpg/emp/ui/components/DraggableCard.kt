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
import com.njpg.emp.core.Card
import com.njpg.emp.data.CardManager
import com.njpg.emp.data.Localization
import com.njpg.emp.ui.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun DraggableCard(
    id: String,
    containerWidthPx: Float,
    containerHeightPx: Float,
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
    val colors = AppTheme.colors
    val savedCard = remember { CardManager.cards.find { it.id == id } }
    var offsetX by remember { mutableStateOf(savedCard?.x ?: 0f) }
    var offsetY by remember { mutableStateOf(savedCard?.y ?: 0f) }

    val width by remember { mutableStateOf(savedCard?.width?.toFloat() ?: 200f) }
    val height by remember { mutableStateOf(savedCard?.height?.toFloat() ?: 200f) }

    fun updatePositions(x: Float, y: Float) {
        offsetX = x.coerceIn(0f, containerWidthPx - width)
        offsetY = y.coerceIn(0f, containerHeightPx - height)
        CardManager.update(Card(id, offsetX, offsetY, width.toInt(), height.toInt()))
    }

    Column(
        modifier = modifier.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }.width(width.dp)
            .height(height.dp).background(colors.hovered).border(1.dp, colors.red)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp).background(colors.pressed).pointerInput(Unit) {
                    detectDragGestures(onDrag = { change, dragAmount ->
                        change.consume()
                        updatePositions(offsetX + dragAmount.x, offsetY + dragAmount.y)
                    }, onDragEnd = {
                        val snapStep = 10f
                        val snappedX = (offsetX / snapStep).roundToInt() * snapStep
                        val snappedY = (offsetY / snapStep).roundToInt() * snapStep
                        updatePositions(snappedX, snappedY)
                    })
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = Localization.tr("drag"), color = colors.white)
        }

        Box(
            modifier = Modifier.padding(16.dp).fillMaxSize(), content = content
        )
    }
}