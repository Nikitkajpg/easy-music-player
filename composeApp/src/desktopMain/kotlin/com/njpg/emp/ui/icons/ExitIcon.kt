package com.njpg.emp.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun exitIcon(strokeColor: Color = AppTheme.colors.white): ImageVector = Builder(
    name = "ExitIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(6f, 6f)
        lineTo(18f, 18f)
        moveTo(6f, 18f)
        lineTo(18f, 6f)
        close()
    }
}.build()