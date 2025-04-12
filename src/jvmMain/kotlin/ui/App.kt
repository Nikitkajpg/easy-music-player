package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import ui.components.*
import ui.components.window.WindowControls

@Composable
fun App(state: WindowState) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentTrack by remember { mutableStateOf("Nothing playing") }
    var currentArtist by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF121212))) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            WindowControls(state)
        }
    }
}