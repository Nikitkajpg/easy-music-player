package ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.components.*

@Composable
fun App() {
    var isPlaying by remember { mutableStateOf(false) }
    var currentTrack by remember { mutableStateOf("Nothing playing") }
    var currentArtist by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlbumArt()
        Spacer(Modifier.height(16.dp))
        TrackInfo(title = currentTrack, artist = currentArtist)
        Spacer(Modifier.height(24.dp))
        PlaybackControls(isPlaying = isPlaying, onPlayPauseToggle = { isPlaying = !isPlaying })
        Spacer(Modifier.height(24.dp))
        ProgressBar()
        Spacer(Modifier.height(24.dp))
        BottomActions()
    }
}