package com.njpg.emp.ui

import androidx.compose.runtime.Immutable
import com.njpg.emp.core.PlayState
import com.njpg.emp.core.ToggleMode
import com.njpg.emp.core.Track

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
