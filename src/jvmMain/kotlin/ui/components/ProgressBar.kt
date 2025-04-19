package ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import util.colorNotWhite
import util.toColor

@Composable
fun ProgressBar() {
    Slider(
        value = 0.3f,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = colorNotWhite,
            activeTrackColor = colorNotWhite
        )
    )
}