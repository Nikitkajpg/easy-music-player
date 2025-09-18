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
import androidx.compose.ui.unit.coerceAtLeast
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
    onResizeEnd: (updatedCard: Card) -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    val colors = AppTheme.colors

    var offsetX by remember { mutableStateOf(card.x) }
    var offsetY by remember { mutableStateOf(card.y) }
    var width by remember { mutableStateOf(card.width) }
    var height by remember { mutableStateOf(card.height) }

    val density = LocalDensity.current

    val minWidth = 150.dp
    val minHeight = 100.dp
    val snapStep = 10f

    LaunchedEffect(card.x, card.y, card.width, card.height) {
        offsetX = card.x
        offsetY = card.y
        width = card.width
        height = card.height
    }

    Box(
        modifier = modifier.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }.size(width.dp, height.dp)
            .background(colors.background).border(1.dp, colors.text)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp).background(colors.hovered).pointerInput(Unit) {
                detectDragGestures(onDrag = { change, dragAmount ->
                    change.consume()
                    val newX =
                        (offsetX + dragAmount.x).coerceIn(0f, containerWidthPx - with(density) { width.dp.toPx() })
                    val newY = (offsetY + dragAmount.y).coerceIn(
                        0f, containerHeightPx - with(density) { height.dp.toPx() })
                    offsetX = newX
                    offsetY = newY
                }, onDragEnd = {
                    val snappedX = (offsetX / snapStep).roundToInt() * snapStep
                    val snappedY = (offsetY / snapStep).roundToInt() * snapStep

                    offsetX = snappedX
                    offsetY = snappedY

                    onDragEnd(card.copy(x = snappedX, y = snappedY))
                })
            }.align(Alignment.TopCenter), contentAlignment = Alignment.Center
        ) {
            Text(text = Localization.tr("drag"), color = colors.icon)
        }

        Box(
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            content()
        }

        Box(
            modifier = Modifier.size(24.dp).align(Alignment.BottomEnd).background(colors.hovered).pointerInput(Unit) {
                detectDragGestures(onDrag = { change, dragAmount ->
                    change.consume()
                    val newWidth = (width.dp + dragAmount.x.toDp()).coerceAtLeast(minWidth.toPx().dp)
                    val newHeight = (height.dp + dragAmount.y.toDp()).coerceAtLeast(minHeight.toPx().dp)
                    width = newWidth.value.toInt()
                    height = newHeight.value.toInt()
                }, onDragEnd = {
                    val snappedWidth = ((width / snapStep).roundToInt() * snapStep).coerceAtLeast(minWidth.toPx())
                    val snappedHeight = ((height / snapStep).roundToInt() * snapStep).coerceAtLeast(minHeight.toPx())

                    width = snappedWidth.toInt()
                    height = snappedHeight.toInt()

                    onResizeEnd(card.copy(width = width, height = height))
                })
            })
    }
}