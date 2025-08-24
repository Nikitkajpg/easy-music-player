package com.njpg.emp.ui.util.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.animatedAppColors

@Composable
fun selectFolderIcon(strokeColor: Color = animatedAppColors().white): ImageVector = Builder(
    name = "SelectFolder", defaultWidth = 24.dp, defaultHeight = 24.dp, viewportWidth = 24f, viewportHeight = 24f
).apply {
    path(
        fill = SolidColor(Color.Unspecified),
        stroke = SolidColor(strokeColor),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
        strokeLineJoin = StrokeJoin.Round,
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(5f, 6f)
        lineTo(10f, 6f)
        lineTo(12f, 8f)
        lineTo(19f, 8f)
        lineTo(19f, 18f)
        lineTo(5f, 18f)
        lineTo(5f, 6f)

        close()
    }
}.build()