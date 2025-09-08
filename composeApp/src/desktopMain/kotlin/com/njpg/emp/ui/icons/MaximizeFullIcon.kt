package com.njpg.emp.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun maximizeFullIcon(strokeColor: Color = AppTheme.colors.white): ImageVector = Builder(
    name = "MaximizeFullIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(5f, 5f)
        lineTo(19f, 5f)
        lineTo(19f, 19f)
        lineTo(5f, 19f)
        close()
    }
}.build()