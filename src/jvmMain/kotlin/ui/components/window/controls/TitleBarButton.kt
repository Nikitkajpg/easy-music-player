package ui.components.window.controls

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun TitleBarButton(
    icon: Painter,
    onClick: () -> Unit,
    contentDescription: String,
    backgroundColor: Color = Color(0xFF1A1A1A),
    borderColor: Color = Color(0xFFFFC107)
) {

    Box(
        modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(backgroundColor)
            .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(8.dp)).clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }
}