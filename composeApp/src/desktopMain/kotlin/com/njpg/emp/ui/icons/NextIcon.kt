package com.njpg.emp.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun nextIcon(strokeColor: Color = AppTheme.colors.white): ImageVector = Builder(
    name = "NextIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    val cornerRadius = 2f

    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(4f, 5f + cornerRadius)
        lineTo(4f, 19f - cornerRadius)
        quadTo(4f, 19f, 5.79f, 18f + 0.11f)
        lineTo(16.21f, 12.89f)
        quadTo(18f, 12f, 16.21f, 11.11f)
        lineTo(5.79f, 5.89f)
        quadTo(4f, 5f, 4f, 5f + cornerRadius)
        close()

        moveTo(20f, 5f)
        lineTo(20f, 19f)
        close()
    }
}.build()