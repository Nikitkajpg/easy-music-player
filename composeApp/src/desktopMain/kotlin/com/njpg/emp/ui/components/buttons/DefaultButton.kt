package com.njpg.emp.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.animatedAppColors
import com.njpg.emp.ui.components.TooltipWrapper

/**
 * Кнопка с подсказкой (tooltip) и визуальной индикацией состояний: обычное, наведено, нажато.
 *
 * Поддерживает настраиваемые цвета для состояний:
 * - [backgroundColor] — обычное состояние,
 * - [pressedColor] — при нажатии,
 * - [hoveredColor] — при наведении.
 *
 * Цвета по умолчанию берутся из текущей темы через [animatedAppColors].
 *
 * @param backgroundColor [Color] цвет кнопки в обычном состоянии.
 * @param pressedColor [Color] цвет кнопки при нажатии.
 * @param hoveredColor [Color] цвет кнопки при наведении.
 * @param tooltipText [String] текст подсказки, отображаемой при наведении.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultButton(
    backgroundColor: Color = animatedAppColors().transparent,
    pressedColor: Color = animatedAppColors().pressed,
    hoveredColor: Color = animatedAppColors().hovered,
    cornerRadius: Dp = 8.dp,
    tooltipText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    var hovered by remember { mutableStateOf(false) }

    val currentBackground = when {
        pressed -> pressedColor
        hovered -> hoveredColor
        else -> backgroundColor
    }

    TooltipWrapper(
        tooltipText = tooltipText
    ) {
        Box(
            modifier = modifier.clip(RoundedCornerShape(cornerRadius)).pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    pressed = true
                    try {
                        awaitRelease()
                    } finally {
                        pressed = false
                    }
                    onClick()
                })
        }.onPointerEvent(PointerEventType.Enter) { hovered = true }
            .onPointerEvent(PointerEventType.Exit) { hovered = false }.background(currentBackground).size(36.dp),
            contentAlignment = Alignment.Center,
            content = content
        )
    }
}