package com.njpg.emp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TooltipWrapper(tooltipText: String, content: @Composable BoxScope.() -> Unit) {
    val colors = AppTheme.colors
    TooltipArea(
        delayMillis = 1500, tooltip = {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(colors.hovered)
                    .border(width = 1.dp, color = colors.redPressed, RoundedCornerShape(8.dp)).padding(6.dp)
            ) {
                Text(tooltipText, color = colors.white)
            }
        }, tooltipPlacement = TooltipPlacement.CursorPoint(offset = DpOffset(0.dp, 16.dp))
    ) {
        Box(content = content)
    }
}