package com.njpg.emp.ui.components

import com.njpg.emp.core.ToggleMode

sealed interface MiddlePanelEvent {
    data object Previous : MiddlePanelEvent
    data object Next : MiddlePanelEvent
    data class PlayPauseToggle(val playing: Boolean) : MiddlePanelEvent
    data class ToggleModeChanged(val mode: ToggleMode) : MiddlePanelEvent
    data class Seek(val position: Float) : MiddlePanelEvent
    data class VolumeChanged(val volume: Float) : MiddlePanelEvent
    data object Blacklist : MiddlePanelEvent
}