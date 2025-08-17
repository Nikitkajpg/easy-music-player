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
import com.njpg.emp.ui.components.TooltipWrapper
import com.njpg.emp.ui.util.animatedAppColors

/**
 * Кнопка-переключатель с подсказкой (tooltip) и визуальной индикацией состояний.
 *
 * Состояния кнопки:
 * - обычное
 * - наведено
 * - нажато
 * - переключено (toggled)
 *
 * Поддерживает настраиваемые цвета для каждого состояния и анимации темы через [animatedAppColors].
 *
 * @param isToggled [Boolean] текущее состояние переключателя (включено/выключено).
 * @param onToggle Лямбда `(Boolean) -> Unit`, вызываемая при изменении состояния переключателя.
 * @param backgroundColor [Color] цвет кнопки в обычном состоянии (по умолчанию прозрачный).
 * @param pressedColor [Color] цвет кнопки при нажатии.
 * @param hoveredColor [Color] цвет кнопки при наведении.
 * @param toggledColor [Color] цвет кнопки в состоянии переключено.
 * @param toggledHoveredColor [Color] цвет кнопки при наведении и переключенном состоянии.
 * @param tooltipText [String] текст подсказки, отображаемый при наведении.
 * @param content Composable-контент кнопки, получает текущее состояние [isToggled].
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultToggleButton(
    isToggled: Boolean,
    onToggle: (Boolean) -> Unit,
    backgroundColor: Color = animatedAppColors().transparent,
    pressedColor: Color = animatedAppColors().pressed,
    hoveredColor: Color = animatedAppColors().hovered,
    toggledColor: Color = animatedAppColors().yellow,
    toggledHoveredColor: Color = animatedAppColors().yellowToggled,
    tooltipText: String,
    cornerRadius: Dp = 8.dp,
    onClick: () -> Unit,
    content: @Composable BoxScope.(isToggled: Boolean) -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    var hovered by remember { mutableStateOf(false) }

    val currentBackground = when {
        pressed -> pressedColor
        hovered && isToggled -> toggledHoveredColor
        hovered -> hoveredColor
        isToggled -> toggledColor
        else -> backgroundColor
    }

    TooltipWrapper(
        tooltipText = tooltipText
    ) {
        Box(
            modifier = Modifier.clip(RoundedCornerShape(cornerRadius)).pointerInput(isToggled) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        try {
                            awaitRelease()
                        } finally {
                            pressed = false
                        }
                        onToggle(!isToggled)
                        onClick()
                    })
            }.onPointerEvent(PointerEventType.Enter) { hovered = true }
                .onPointerEvent(PointerEventType.Exit) { hovered = false }.background(currentBackground).size(36.dp),
            contentAlignment = Alignment.Center,
        ) {
            content(isToggled)
        }
    }
}