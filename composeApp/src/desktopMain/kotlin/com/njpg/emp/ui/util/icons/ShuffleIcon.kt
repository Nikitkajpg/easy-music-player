package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.AppColors

fun shuffleIcon(strokeColor: Color = AppColors.white): ImageVector = Builder(
    name = "ShuffleIcon", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(1f, 6f)
        lineTo(6f, 6f)
        lineTo(9f, 9f)

        moveTo(15f, 15f)
        lineTo(18f, 18f)
        lineTo(23f, 18f)

        moveTo(1f, 18f)
        lineTo(6f, 18f)
        lineTo(18f, 6f)
        lineTo(23f, 6f)

        moveTo(20f, 3f)
        lineTo(23f, 6f)
        lineTo(20f, 9f)

        moveTo(20f, 15f)
        lineTo(23f, 18f)
        lineTo(20f, 21f)
    }
}.build()