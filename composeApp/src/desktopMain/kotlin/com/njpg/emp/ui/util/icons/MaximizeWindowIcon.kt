package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.DarkThemeColors

fun maximizeWindowIcon(strokeColor: Color = DarkThemeColors.white): ImageVector = Builder(
    name = "MaximizeWindowIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(7f, 4f)
        lineTo(20f, 4f)
        lineTo(20f, 17f)
        lineTo(17f, 17f)
        lineTo(17f, 7f)
        lineTo(7f, 7f)
        close()
    }
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(5f, 7f)
        lineTo(17f, 7f)
        lineTo(17f, 19f)
        lineTo(5f, 19f)
        close()
    }
}.build()