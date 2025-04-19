package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import util.colorNotWhite
import util.toColor

@Composable
fun PlaybackControls(isPlaying: Boolean, onPlayPauseToggle: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = { /* previous track */ }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous", tint = colorNotWhite) // TODO: 12.04.2025 icon
        }
        IconButton(onClick = onPlayPauseToggle) {
            Icon(
                if (isPlaying) Icons.Default.KeyboardArrowUp else Icons.Default.PlayArrow, // TODO: 12.04.2025 icon
                contentDescription = "Play/Pause",
                tint = colorNotWhite
            )
        }
        IconButton(onClick = { /* next track */ }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next", tint = colorNotWhite) // TODO: 12.04.2025 icon
        }
    }
}