package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.DarkThemeColors

fun blacklistIcon(strokeColor: Color = DarkThemeColors.white): ImageVector = Builder(
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
        moveTo(12f, 2f)
        arcTo(
            horizontalEllipseRadius = 10f,
            verticalEllipseRadius = 10f,
            theta = 0f,
            isMoreThanHalf = true,
            isPositiveArc = true,
            x1 = 12f,
            y1 = 22f
        )
        arcTo(
            horizontalEllipseRadius = 10f,
            verticalEllipseRadius = 10f,
            theta = 0f,
            isMoreThanHalf = true,
            isPositiveArc = true,
            x1 = 12f,
            y1 = 2f
        )
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
        moveTo(6f, 6f)
        lineTo(18f, 18f)
    }
}.build()