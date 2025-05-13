package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njpg.emp.ui.MiddlePanelUiState
import com.njpg.emp.ui.util.AppColors

@Composable
fun MiddlePanel(
    uiState: MiddlePanelUiState, onEvent: (MiddlePanelEvent) -> Unit, discSize: Dp = 240.dp
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(AppColors.background)
            .padding(horizontal = 24.dp, vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.playlistName, color = AppColors.yellow, fontSize = 18.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            VinylSection(
                playState = uiState.playState, discSize = discSize
            )

            Spacer(Modifier.width(24.dp))

            TrackInfoSection(track = uiState.track, toggleMode = uiState.toggleMode, onToggleModeChange = { newMode ->
                onEvent(MiddlePanelEvent.ToggleModeChanged(newMode))
            }, onBlacklist = { onEvent(MiddlePanelEvent.Blacklist) })
        }

        Spacer(Modifier.height(24.dp))

        ProgressSection(
            currentTimeMs = uiState.currentTimeMs,
            durationMs = uiState.durationMs,
            onSeek = { onEvent(MiddlePanelEvent.Seek(it)) })

        Spacer(Modifier.height(16.dp))

        PlaybackControlsSection(
            playState = uiState.playState,
            onPrevious = { onEvent(MiddlePanelEvent.Previous) },
            onPlayPauseToggle = { playing ->
                onEvent(MiddlePanelEvent.PlayPauseToggle(playing))
            },
            onNext = { onEvent(MiddlePanelEvent.Next) })

        Spacer(Modifier.height(24.dp))

        VolumeSection(
            volume = uiState.volume, onVolumeChange = { onEvent(MiddlePanelEvent.VolumeChanged(it)) })
    }
}