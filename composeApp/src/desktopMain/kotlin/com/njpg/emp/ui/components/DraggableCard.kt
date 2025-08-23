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
import com.njpg.emp.data.animatedAppColors
import kotlin.math.roundToInt

@Composable
fun DraggableCard(
    id: String, modifier: Modifier = Modifier, content: @Composable (BoxScope.() -> Unit)
) {
    val savedCard = remember { CardManager.cards.find { it.id == id } }
    var offsetX by remember { mutableStateOf(savedCard?.x ?: 0f) }
    var offsetY by remember { mutableStateOf(savedCard?.y ?: 0f) }
    val width by remember { mutableStateOf(savedCard?.width ?: 200) }
    val height by remember { mutableStateOf(savedCard?.height ?: 200) }

    fun updatePositions(x: Float, y: Float) {
        offsetX = x
        offsetY = y.coerceAtLeast(0f)
        CardManager.update(Card(id, offsetX, offsetY, width, height))
    }

    Column(modifier = modifier.offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .background(animatedAppColors().hovered).border(1.dp, animatedAppColors().red)) {
        Box(
            modifier = Modifier.fillMaxWidth().height(32.dp).background(animatedAppColors().pressed)
                .pointerInput(Unit) {
                    detectDragGestures(onDrag = { change, dragAmount ->
                        change.consume()
                        updatePositions(offsetX + dragAmount.x, offsetY + dragAmount.y)
                    }, onDragEnd = {
                        val snapStep = 10f
                        offsetX = (offsetX / snapStep).roundToInt() * snapStep
                        offsetY = ((offsetY / snapStep).roundToInt() * snapStep).coerceAtLeast(0f)
                        CardManager.update(Card(id, offsetX, offsetY, width, height))
                    })
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = Localization.tr("drag"), color = animatedAppColors().white)
        }

        Box(
            modifier = Modifier.padding(16.dp), content = content
        )
    }
}