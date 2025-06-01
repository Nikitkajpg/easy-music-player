package com.njpg.emp.ui.components

import androidx.compose.runtime.Composable
import com.njpg.emp.ui.BottomPanelUiState

@Composable
fun BottomPanel(
    uiState: BottomPanelUiState, onEvent: (BottomPanelEvent) -> Unit
) {
    BottomControlsSection(
        uiState, onOpenFolder = { folderPath ->
            onEvent(BottomPanelEvent.OpenFolder(folderPath))
        })
}