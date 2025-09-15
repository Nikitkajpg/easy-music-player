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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.njpg.emp.core.Card
import com.njpg.emp.data.Localization
import com.njpg.emp.ui.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun DraggableCard(
    card: Card,
    containerWidthPx: Float,
    containerHeightPx: Float,
    modifier: Modifier = Modifier,
    onDragEnd: (updatedCard: Card) -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    val colors = AppTheme.colors

    var offsetX by remember { mutableStateOf(card.x) }
    var offsetY by remember { mutableStateOf(card.y) }

    val density = LocalDensity.current

    val cardWidthPx = with(density) { card.width.dp.toPx() }
    val cardHeightPx = with(density) { card.height.dp.toPx() }

    LaunchedEffect(card.x, card.y) {
        offsetX = card.x
        offsetY = card.y
    }

    Column(modifier = modifier.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .size(card.width.dp, card.height.dp).background(colors.hovered).border(1.dp, colors.red)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp).background(colors.pressed).pointerInput(Unit) {
                    detectDragGestures(onDrag = { change, dragAmount ->
                        change.consume()
                        val newX = (offsetX + dragAmount.x).coerceIn(0f, containerWidthPx - cardWidthPx)
                        val newY = (offsetY + dragAmount.y).coerceIn(0f, containerHeightPx - cardHeightPx)
                        offsetX = newX
                        offsetY = newY
                    }, onDragEnd = {
                        val snapStep = 10f
                        val snappedX = (offsetX / snapStep).roundToInt() * snapStep
                        val snappedY = (offsetY / snapStep).roundToInt() * snapStep

                        offsetX = snappedX
                        offsetY = snappedY

                        onDragEnd(card.copy(x = snappedX, y = snappedY))
                    })
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = Localization.tr("drag"), color = colors.icon)
        }

        Box(
            modifier = Modifier.padding(16.dp).fillMaxSize(), content = content
        )
    }
}