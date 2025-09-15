package com.njpg.emp.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun previousIcon(strokeColor: Color = AppTheme.colors.icon): ImageVector = Builder(
    name = "PreviousIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
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
        moveTo(19f, 5f + cornerRadius)
        lineTo(19f, 19f - cornerRadius)
        quadTo(19f, 19f, 18.21f, 18f + 0.11f)
        lineTo(6.79f, 12.89f)
        quadTo(5f, 12f, 6.79f, 11.11f)
        lineTo(18.21f, 5.89f)
        quadTo(19f, 5f, 19f, 5f + cornerRadius)
        close()

        moveTo(4f, 5f)
        lineTo(4f, 19f)
    }
}.build()