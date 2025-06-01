package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njpg.emp.core.PlayState
import com.njpg.emp.ui.MiddlePanelUiState
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.components.buttons.DefaultToggleButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.icons.nextIcon
import com.njpg.emp.ui.util.icons.pauseIcon
import com.njpg.emp.ui.util.icons.playIcon
import com.njpg.emp.ui.util.icons.previousIcon

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

            TrackInfoSection(track = uiState.track, toggleMode = uiState.toggleMode, onToggleModeChange = { newMode ->
                onEvent(MiddlePanelEvent.ToggleModeChanged(newMode))
            }, onBlacklist = { onEvent(MiddlePanelEvent.Blacklist) })
        }

        VolumeSection(
            volume = uiState.volume, onVolumeChange = { onEvent(MiddlePanelEvent.VolumeChanged(it)) })

        ProgressSection(
            currentTimeMs = uiState.currentTimeMs,
            durationMs = uiState.durationMs,
            onSeek = { onEvent(MiddlePanelEvent.Seek(it)) })

        PlaybackControlsSection(
            playState = uiState.playState,
            onPrevious = { onEvent(MiddlePanelEvent.Previous) },
            onPlayPauseToggle = { playing ->
                onEvent(MiddlePanelEvent.PlayPauseToggle(playing))
            },
            onNext = { onEvent(MiddlePanelEvent.Next) })
    }
}

@Composable
fun PlaybackControlsSection(
    playState: PlayState,
    onPrevious: () -> Unit,
    onPlayPauseToggle: (Boolean) -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultButton(tooltipText = "Previous", onClick = onPrevious) {
            Icon(
                imageVector = previousIcon(),
                contentDescription = "Previous",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        DefaultToggleButton(
            toggledColor = AppColors.background,
            toggledHoveredColor = AppColors.hovered,
            isToggled = playState == PlayState.PLAYING,
            onToggle = onPlayPauseToggle,
            tooltipText = if (playState == PlayState.PLAYING) "Pause" else "Play",
            onClick = {},
        ) { isPlaying ->
            Icon(
                imageVector = if (isPlaying) pauseIcon() else playIcon(),
                contentDescription = "Play/Pause",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        DefaultButton(tooltipText = "Next", onClick = onNext) {
            Icon(
                imageVector = nextIcon(),
                contentDescription = "Next",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}