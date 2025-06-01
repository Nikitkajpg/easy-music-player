package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.njpg.emp.ui.BottomPanelUiState

@Composable
fun RightPanel(
    uiState: BottomPanelUiState, onEvent: (BottomPanelEvent) -> Unit, modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        RightControlsSection(
            uiState, onOpenFolder = { folderPath ->
                onEvent(BottomPanelEvent.OpenFolder(folderPath))
            })
    }
}