package com.njpg.emp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.ui.components.MiddlePanel
import com.njpg.emp.ui.components.MiddlePanelEvent
import com.njpg.emp.ui.components.MiddlePanelUiState
import com.njpg.emp.ui.components.TopPanel
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.PlayState
import com.njpg.emp.ui.util.ToggleMode

@Composable
fun WindowScope.App(windowState: WindowState) {
    var middlePanelUiState by remember {
        mutableStateOf(
            MiddlePanelUiState(
                playlistName = "Demo",
                track = null,
                playState = PlayState.PAUSE,
                toggleMode = ToggleMode.NONE,
                currentTimeMs = 0L,
                durationMs = 0L,
                volume = 0.7f
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(AppColors.background)
    ) {
        TopPanel(windowState)
        MiddlePanel(
            uiState = middlePanelUiState,
            onEvent = {event ->
                when (event) {
                    is MiddlePanelEvent.Seek ->
                        middlePanelUiState = middlePanelUiState.copy(
                            currentTimeMs = (event.position * middlePanelUiState.durationMs).toLong()
                        )
                    is MiddlePanelEvent.VolumeChanged ->
                        middlePanelUiState = middlePanelUiState.copy(volume = event.volume)
                    is MiddlePanelEvent.PlayPauseToggle ->
                        middlePanelUiState = middlePanelUiState.copy(
                            playState = if (event.playing)
                            PlayState.PLAYING
                            else
                            PlayState.PAUSE
                        )
                    is MiddlePanelEvent.ToggleModeChanged ->
                        middlePanelUiState = middlePanelUiState.copy(toggleMode = event.mode)
                    else -> {}
                }
            }
        )
//        BottomPanel()
    }
}