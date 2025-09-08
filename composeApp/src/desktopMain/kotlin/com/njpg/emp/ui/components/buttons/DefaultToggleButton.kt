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
import com.njpg.emp.ui.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultToggleButton(
    isToggled: Boolean,
    onToggle: (Boolean) -> Unit,
    backgroundColor: Color = AppTheme.colors.transparent,
    pressedColor: Color = AppTheme.colors.pressed,
    hoveredColor: Color = AppTheme.colors.hovered,
    toggledColor: Color = AppTheme.colors.yellow,
    toggledHoveredColor: Color = AppTheme.colors.yellowToggled,
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