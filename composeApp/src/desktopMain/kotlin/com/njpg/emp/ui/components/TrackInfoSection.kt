package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njpg.emp.core.ToggleMode
import com.njpg.emp.core.Track
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.components.buttons.DefaultToggleButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.icons.blacklistIcon
import com.njpg.emp.ui.util.icons.preferencesIcon
import com.njpg.emp.ui.util.icons.shuffleIcon

@Composable
fun TrackInfoSection(
    track: Track?,
    toggleMode: ToggleMode,
    onToggleModeChange: (ToggleMode) -> Unit,
    onBlacklist: () -> Unit,
) {
    Column(modifier = Modifier) {
        Text(
            text = track?.title ?: "Title", color = AppColors.white, fontSize = 20.sp, maxLines = 1
        )
        Text(
            text = track?.artist ?: "Artist", color = AppColors.white, fontSize = 14.sp, maxLines = 1
        )

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultToggleButton(
                isToggled = toggleMode == ToggleMode.SHUFFLE,
                onToggle = { toggled ->
                    onToggleModeChange(if (toggled) ToggleMode.SHUFFLE else ToggleMode.NONE)
                },
                tooltipText = "Shuffle",
                onClick = {},
            ) { isToggled ->
                Icon(
                    imageVector = shuffleIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                    contentDescription = "Shuffle",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            DefaultToggleButton(
                isToggled = toggleMode == ToggleMode.PREFERENCES,
                onToggle = { toggled ->
                    onToggleModeChange(if (toggled) ToggleMode.PREFERENCES else ToggleMode.NONE)
                },
                tooltipText = "Preference system",
                onClick = {},
            ) { isToggled ->
                Icon(
                    imageVector = preferencesIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                    contentDescription = "Preference system",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            DefaultButton(
                tooltipText = "To blacklist",
                onClick = onBlacklist,
            ) {
                Icon(
                    imageVector = blacklistIcon(),
                    contentDescription = "To blacklist",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}