package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.AppColors

fun preferenceIcon(strokeColor: Color = AppColors.white): ImageVector = Builder(
    name = "PreferenceIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(strokeColor),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(12f, 2f)
        lineTo(9.65f, 8.76f)
        lineTo(2.49f, 8.91f)
        lineTo(8.2f, 13.24f)
        lineTo(6.12f, 20.09f)
        lineTo(12f, 16f)
        lineTo(17.88f, 20.09f)
        lineTo(15.8f, 13.24f)
        lineTo(21.51f, 8.91f)
        lineTo(14.35f, 8.76f)
        close()
    }
}.build()