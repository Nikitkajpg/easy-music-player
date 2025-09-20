package com.njpg.emp.ui.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.njpg.emp.data.PlaylistManager
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun DefaultPlaylistCard() {
    val playlist = PlaylistManager.getDefaultPlaylist()
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            if (playlist != null) {
                Text(text = playlist.name, color = AppTheme.colors.text)
            }
            playlist?.tracks?.forEach { track ->
                Text(text = track.title, color = AppTheme.colors.text)
            }
        }
    }
}