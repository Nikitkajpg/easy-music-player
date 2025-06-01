package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.AppColors

fun backIcon(strokeColor: Color = AppColors.white): ImageVector = Builder(
    name = "BackIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(4f, 12f)
        lineTo(20f, 12f)
        moveTo(4f, 12f)
        lineTo(10f, 6f)
        moveTo(4f, 12f)
        lineTo(10f, 18f)
        close()
    }
}.build()