package com.njpg.emp.ui

import androidx.compose.runtime.Immutable
import com.njpg.emp.core.Track
import com.njpg.emp.ui.util.PlayState
import com.njpg.emp.ui.util.ToggleMode

@Immutable
data class MiddlePanelUiState(
    val playlistName: String,
    val track: Track?,
    val playState: PlayState,
    val toggleMode: ToggleMode,
    val currentTimeMs: Long,
    val durationMs: Long,
    val volume: Float
)
