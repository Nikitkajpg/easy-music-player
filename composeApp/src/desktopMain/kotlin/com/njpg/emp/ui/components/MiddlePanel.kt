package com.njpg.emp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.njpg.emp.core.Track
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.components.buttons.DefaultToggleButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.PlayState
import com.njpg.emp.ui.util.ToggleMode
import com.njpg.emp.ui.util.icons.*
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.vinylPlayer
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun MiddlePanel() {
    val track: Track? = null
    var toggleMode by remember { mutableStateOf(ToggleMode.NONE) }
    var playState by remember { mutableStateOf(PlayState.PAUSE) }

    Column(
        modifier = Modifier.fillMaxWidth().background(AppColors.background)
            .padding(horizontal = 24.dp, vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Current playlist", color = AppColors.yellow, fontSize = 18.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(300.dp).aspectRatio(1f)
            ) {
                Image(
                    painter = painterResource(Res.drawable.vinylPlayer),
                    contentDescription = "Vinyl player",
                    modifier = Modifier.fillMaxSize()
                )

                VinylDisc(
                    playState = playState, size = 240.dp, modifier = Modifier.align(Alignment.Center).zIndex(1f)
                )

                VinylStick(playState)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = track?.title ?: "Title", color = AppColors.white, fontSize = 20.sp, maxLines = 1
                )
                Text(
                    text = track?.artist ?: "Artist", color = AppColors.white, fontSize = 14.sp, maxLines = 1
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultToggleButton(isToggled = toggleMode == ToggleMode.SHUFFLE, onToggle = { isToggled ->
                        toggleMode = if (isToggled) ToggleMode.SHUFFLE else ToggleMode.NONE
                    }, tooltipText = "Shuffle", onClick = {}) { isToggled ->
                        Icon(
                            imageVector = shuffleIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                            contentDescription = "Shuffle",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultToggleButton(isToggled = toggleMode == ToggleMode.PREFERENCES, onToggle = { isToggled ->
                        toggleMode = if (isToggled) ToggleMode.PREFERENCES else ToggleMode.NONE
                    }, tooltipText = "Preferences", onClick = {}) { isToggled ->
                        Icon(
                            imageVector = preferencesIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                            contentDescription = "Preferences",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultButton(
                        tooltipText = "To blacklist", onClick = {}) {
                        Icon(
                            imageVector = blacklistIcon(),
                            contentDescription = "To blacklist",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                val durationMs = /*track?.durationMs ?: 0L*/500L
                val sliderPosition = if (durationMs == 0L) 0f else /*currentTimeMs*/ 350L / durationMs.toFloat()
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(formatTime(/*currentTimeMs*/350L), color = AppColors.white, fontSize = 12.sp)
                    Slider(
                        value = sliderPosition.coerceIn(0f, 1f),
                        onValueChange = {}/*onSeek*/,
                        modifier = Modifier.weight(1f)
                    )
                    Text(formatTime(durationMs), color = AppColors.white, fontSize = 12.sp)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DefaultButton(
                        tooltipText = "Previous", onClick = {}) {
                        Icon(
                            imageVector = previousIcon(),
                            contentDescription = "Previous",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultToggleButton(
                        toggledColor = AppColors.background,
                        toggledHoveredColor = AppColors.hovered,
                        isToggled = playState == PlayState.PLAYING,
                        onToggle = { playing ->
                            playState = if (playing) PlayState.PLAYING else PlayState.PAUSE
                        },
                        tooltipText = if (playState == PlayState.PLAYING) "Pause" else "Play",
                        onClick = {}) { isPlaying ->
                        Icon(
                            imageVector = if (isPlaying) pauseIcon() else playIcon(),
                            contentDescription = "Play/Pause",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultButton(
                        tooltipText = "Next", onClick = {}) {
                        Icon(
                            imageVector = nextIcon(),
                            contentDescription = "Next",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null, tint = AppColors.white)
            Slider(
                value = 100f/*volume*/.coerceIn(0f, 1f),
                onValueChange = {}/*onVolumeChange*/,
                modifier = Modifier.width(200.dp)
            )
        }
    }
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val totalSeconds = (ms / 1000f).roundToInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}