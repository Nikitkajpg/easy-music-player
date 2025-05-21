package com.njpg.emp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.BottomPanelUiState
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.icons.openFolder

@Composable
fun BottomPanel(
    uiState: BottomPanelUiState,
    onEvent: (BottomPanelEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(AppColors.background)
            .padding(horizontal = 24.dp, vertical = 16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultButton(tooltipText = "Open folder", onClick = { onEvent(BottomPanelEvent.OpenFolder) }) {
            Icon(
                imageVector = openFolder(),
                contentDescription = "Open folder",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}