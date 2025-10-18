package com.njpg.emp.ui.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.njpg.emp.data.Localization
import com.njpg.emp.data.PlayerController
import com.njpg.emp.ui.theme.AppTheme

@Composable
fun InfoCard() {
    val currentTrack by PlayerController.currentTrack.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = currentTrack?.title ?: Localization.tr("track_not_selected"), color = AppTheme.colors.text)
            Text(text = currentTrack?.artist ?: Localization.tr("unknown_artist"), color = AppTheme.colors.text)
        }
    }
}