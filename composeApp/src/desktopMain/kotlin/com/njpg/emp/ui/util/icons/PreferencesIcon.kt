package com.njpg.emp.ui.util.icons

import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.DarkThemeColors

fun preferencesIcon(strokeColor: Color = DarkThemeColors.white): ImageVector = Builder(
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
        moveTo(6f, 1f)
        lineTo(6f, 23f)

        moveTo(3f, 20f)
        lineTo(6f, 23f)
        lineTo(9f, 20f)

        moveTo(18f, 1f)
        lineTo(18f, 23f)

        moveTo(15f, 4f)
        lineTo(18f, 1f)
        lineTo(21f, 4f)
        close()
    }
}.build()