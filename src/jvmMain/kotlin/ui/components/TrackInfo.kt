package ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import util.colorGray
import util.colorNotWhite
import util.toColor

@Composable
fun TrackInfo(title: String, artist: String) {
    Text(
        title,
        color = colorNotWhite,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

    Text(
        artist,
        color = colorGray,
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}