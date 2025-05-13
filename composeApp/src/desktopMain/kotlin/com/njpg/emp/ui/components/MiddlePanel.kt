package com.njpg.emp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Immutable
data class MiddlePanelUiState(
    val playlistName: String,
    val track: Track?,
    val playState: PlayState,
    val toggleMode: ToggleMode,
    val currentTimeMs: Long,
    val durationMs: Long,
    val volume: Float
)

sealed interface MiddlePanelEvent {
    data object Previous : MiddlePanelEvent
    data object Next : MiddlePanelEvent
    data class PlayPauseToggle(val playing: Boolean) : MiddlePanelEvent
    data class ToggleModeChanged(val mode: ToggleMode) : MiddlePanelEvent
    data class Seek(val position: Float) : MiddlePanelEvent
    data class VolumeChanged(val volume: Float) : MiddlePanelEvent
    data object Blacklist : MiddlePanelEvent
}

@Composable
fun MiddlePanel(
    uiState: MiddlePanelUiState,
    onEvent: (MiddlePanelEvent) -> Unit,
    discSize: Dp = 240.dp
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.background)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.playlistName,
            color = AppColors.yellow,
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            /*Box(
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
            }*/

            /*Column(modifier = Modifier.weight(1f)) {
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
            }*/

            VinylSection(
                playState = uiState.playState,
                discSize = discSize
            )

            Spacer(Modifier.width(24.dp))

            TrackInfoSection(
                track = uiState.track,
                toggleMode = uiState.toggleMode,
                onToggleModeChange = { newMode ->
                    onEvent(MiddlePanelEvent.ToggleModeChanged(newMode))
                },
                onBlacklist = {onEvent(MiddlePanelEvent.Blacklist)}
            )
        }

        Spacer(Modifier.height(24.dp))

        ProgressSection(
            currentTimeMs = uiState.currentTimeMs,
            durationMs = uiState.durationMs,
            onSeek = {onEvent(MiddlePanelEvent.Seek(it))}
        )

        Spacer(Modifier.height(16.dp))

        PlaybackControlsSection(
            playState = uiState.playState,
            onPrevious = {onEvent(MiddlePanelEvent.Previous)},
            onPlayPauseToggle = {playing ->
                onEvent(MiddlePanelEvent.PlayPauseToggle(playing))
            },
            onNext = {onEvent(MiddlePanelEvent.Next)}
        )

        Spacer(Modifier.height(24.dp))

        VolumeSection(
            volume = uiState.volume,
            onVolumeChange = {onEvent(MiddlePanelEvent.VolumeChanged(it))}
        )
        /*Row(
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
        }*/
    }
}

@Composable
private fun VinylSection(
    playState: PlayState,
    discSize: Dp
) {
    Box(
        modifier = Modifier
            .size(discSize + 60.dp)
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(Res.drawable.vinylPlayer),
            contentDescription = "Vinyl player",
            modifier = Modifier.fillMaxSize()
        )

        VinylDisc(
            playState = playState,
            size = discSize,
            modifier = Modifier.align(Alignment.Center)
        )

        VinylStick(playState)
    }
}

@Composable
private fun TrackInfoSection(
    track: Track?,
    toggleMode: ToggleMode,
    onToggleModeChange: (ToggleMode) -> Unit,
    onBlacklist: () -> Unit,
) {
    Column(modifier = Modifier/*.weight(1f)*/) {
        /* track title & artist */
        Text(
            text = track?.title ?: "Title",
            color = AppColors.white,
            fontSize = 20.sp,
            maxLines = 1
        )
        Text(
            text = track?.artist ?: "Artist",
            color = AppColors.white,
            fontSize = 14.sp,
            maxLines = 1
        )

        Spacer(Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            /* Shuffle */
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

            /* Preferences */
            DefaultToggleButton(
                isToggled = toggleMode == ToggleMode.PREFERENCES,
                onToggle = { toggled ->
                    onToggleModeChange(if (toggled) ToggleMode.PREFERENCES else ToggleMode.NONE)
                },
                tooltipText = "Preferences",
                onClick = {},
            ) { isToggled ->
                Icon(
                    imageVector = preferencesIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                    contentDescription = "Preferences",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            /* Blacklist */
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

@Composable
private fun ProgressSection(
    currentTimeMs: Long,
    durationMs: Long,
    onSeek: (Float) -> Unit,
) {
    val sliderPosition = if (durationMs == 0L) 0f else currentTimeMs / durationMs.toFloat()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(formatTime(currentTimeMs), color = AppColors.white, fontSize = 12.sp)
        Slider(
            value = sliderPosition.coerceIn(0f, 1f),
            onValueChange = onSeek,
            modifier = Modifier.weight(1f)
        )
        Text(formatTime(durationMs), color = AppColors.white, fontSize = 12.sp)
    }
}

@Composable
private fun PlaybackControlsSection(
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

@Composable
private fun VolumeSection(
    volume: Float,
    onVolumeChange: (Float) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = null, tint = AppColors.white)
        Slider(
            value = volume.coerceIn(0f, 1f),
            onValueChange = onVolumeChange,
            modifier = Modifier.width(200.dp)
        )
    }
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val totalSeconds = (ms / 1000f).roundToInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}