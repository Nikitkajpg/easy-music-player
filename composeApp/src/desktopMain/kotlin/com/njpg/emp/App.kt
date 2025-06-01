package com.njpg.emp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.core.PlayState
import com.njpg.emp.core.ToggleMode
import com.njpg.emp.ui.BottomPanelUiState
import com.njpg.emp.ui.MiddlePanelUiState
import com.njpg.emp.ui.components.*
import com.njpg.emp.ui.util.AppColors
import java.nio.file.Paths

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

    var bottomPanelUiState by remember {
        mutableStateOf(
            BottomPanelUiState(
                folderPath = Paths.get(System.getProperty("user.home")).toString()
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(AppColors.background)
    ) {
        TopPanel(windowState)
        MiddlePanel(
            uiState = middlePanelUiState, onEvent = { event ->
                when (event) {
                    is MiddlePanelEvent.Seek -> middlePanelUiState = middlePanelUiState.copy(
                        currentTimeMs = (event.position * middlePanelUiState.durationMs).toLong()
                    )

                    is MiddlePanelEvent.VolumeChanged -> middlePanelUiState =
                        middlePanelUiState.copy(volume = event.volume)

                    is MiddlePanelEvent.PlayPauseToggle -> middlePanelUiState = middlePanelUiState.copy(
                        playState = if (event.playing) PlayState.PLAYING
                        else PlayState.PAUSE
                    )

                    is MiddlePanelEvent.ToggleModeChanged -> middlePanelUiState =
                        middlePanelUiState.copy(toggleMode = event.mode)

                    else -> {}
                }
            })
        BottomPanel(
            uiState = bottomPanelUiState, onEvent = { event ->
                when (event) {
                    is BottomPanelEvent.OpenFolder -> bottomPanelUiState =
                    bottomPanelUiState.copy(folderPath = event.folderPath)
                }
            })
    }
}