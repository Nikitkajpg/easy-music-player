package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import util.colorGray
import util.colorLightGray
import util.toColor

@Composable
fun AlbumArt() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(colorLightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("Album Art", color = colorGray)
    }
}