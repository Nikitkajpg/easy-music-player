package ui.components.window.controls

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerMoveFilter
import androidx.compose.ui.unit.dp
import util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TitleBarButton(
    icon: Painter,
    onClick: () -> Unit,
    contentDescription: String,
    normalBackground: Color = colorOfBackground,
    hoverBackground: Color = colorLightGray,
    normalBorder: Color = colorDarkYellow,
    hoverBorder: Color = colorYellow
) {
    var hovered by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(if (hovered) hoverBackground else normalBackground)
    val borderColor by animateColorAsState(if (hovered) hoverBorder else normalBorder)

    Box(
        modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(backgroundColor)
            .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(8.dp)).pointerMoveFilter(
                onEnter = {
                    hovered = true
                    false
                },
                onExit = {
                    hovered = false
                    true
                }
            ).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}