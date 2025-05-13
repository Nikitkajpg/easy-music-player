package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.components.buttons.DefaultToggleButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.PlayState
import com.njpg.emp.ui.util.icons.nextIcon
import com.njpg.emp.ui.util.icons.pauseIcon
import com.njpg.emp.ui.util.icons.playIcon
import com.njpg.emp.ui.util.icons.previousIcon

@Composable
fun PlaybackControlsSection(
    playState: PlayState,
    onPrevious: () -> Unit,
    onPlayPauseToggle: (Boolean) -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        /* Previous */
        DefaultButton(tooltipText = "Previous", onClick = onPrevious) {
            Icon(
                imageVector = previousIcon(),
                contentDescription = "Previous",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        /* Play / Pause */
        DefaultToggleButton(
            toggledColor = AppColors.background,
            toggledHoveredColor = AppColors.hovered,
            isToggled = playState == PlayState.PLAYING,
            onToggle = onPlayPauseToggle,
            tooltipText = if (playState == PlayState.PLAYING) "Pause" else "Play",
            onClick = {},
        ) { isPlaying ->
            Icon(
                imageVector = if (isPlaying) pauseIcon() else playIcon(),
                contentDescription = "Play/Pause",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        /* Next */
        DefaultButton(tooltipText = "Next", onClick = onNext) {
            Icon(
                imageVector = nextIcon(),
                contentDescription = "Next",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}