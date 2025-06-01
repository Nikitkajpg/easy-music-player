package com.njpg.emp.ui.components

sealed interface BottomPanelEvent {
    data class OpenFolder(val folderPath: String) : BottomPanelEvent
}