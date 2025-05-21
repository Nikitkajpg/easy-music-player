package com.njpg.emp.ui.components

sealed interface BottomPanelEvent {
    data object OpenFolder : BottomPanelEvent
}