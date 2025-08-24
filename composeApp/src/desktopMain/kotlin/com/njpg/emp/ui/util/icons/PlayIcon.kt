package com.njpg.emp.ui.util.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.animatedAppColors

@Composable
fun playIcon(strokeColor: Color = animatedAppColors().white): ImageVector = Builder(
    name = "PlayIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
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
        moveTo(5f, 5f + cornerRadius)
        lineTo(5f, 19f - cornerRadius)
        quadTo(5f, 19f, 5f + 1.79f, 18f + 0.11f)
        lineTo(17.21f, 12.89f)
        quadTo(19f, 12f, 17.21f, 11.11f)
        lineTo(6.79f, 5.89f)
        quadTo(5f, 5f, 5f, 5f + cornerRadius)
        close()
    }
}.build()