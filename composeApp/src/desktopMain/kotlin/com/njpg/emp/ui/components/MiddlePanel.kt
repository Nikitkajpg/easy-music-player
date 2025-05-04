package com.njpg.emp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.components.buttons.DefaultToggleButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.ToggleMode
import com.njpg.emp.ui.util.icons.blacklistIcon
import com.njpg.emp.ui.util.icons.preferencesIcon
import com.njpg.emp.ui.util.icons.shuffleIcon

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MiddlePanel(
    //todo state
//    isPlaying: Boolean,
//    currentTimeMs: Long,
//    volume: Float,
//    shuffleEnabled: Boolean,
//    currentPlaylist: String,
//    track: Track?,

    //todo events
//    onSeek: (Float) -> Unit,
//    onVolumeChange: (Float) -> Unit,
//    onPrevious: () -> Unit,
//    onPlayPause: () -> Unit,
//    onNext: () -> Unit,
//    onToggleShuffle: () -> Unit,
//    onPreferences: () -> Unit,
//    onMenu: () -> Unit,
//    onBlacklist: () -> Unit,
) {
    var toggleMode by remember { mutableStateOf(ToggleMode.NONE) }

    Column(
        modifier = Modifier.fillMaxWidth().background(AppColors.background)
            .padding(horizontal = 24.dp, vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //todo
        Text(
            text = "Current playlist", color = AppColors.yellow, fontSize = 18.sp
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            /*//todo album cover / disc animation
            val coverModifier = Modifier.size(200.dp)
            /*if (track?.cover != null) {
                Image(
                    painter = BitmapPainter(track.cover*//*.asImageBitmap()*//*),
                    contentDescription = "Cover art",
                    modifier = coverModifier,
                    contentScale = ContentScale.Crop
                )
            } else {*/
                //todo vinyl imitation animation
                val rotation by rememberInfiniteRotation(/*isPlaying*/true)
                Box(
                    modifier = coverModifier
                        .rotate(rotation)
                        .background(color = AppColors.redPressed, shape = CircleShape)
                )
            /*}*/*/

            Spacer(Modifier.width(32.dp))

            //todo track info and sliders
            Column(modifier = Modifier.weight(1f)) {
                /*//todo title and artist
                Text(
                    text = /*track?.title ?: "-"*/"Title",
                    color = AppColors.white,
                    fontSize = 20.sp,
                    maxLines = 1
                )
                Text(
                    text = /*track?.artist ?: "—"*/"Artist",
                    color = AppColors.white,
                    fontSize = 14.sp,
                    maxLines = 1
                )*/

                Spacer(Modifier.height(24.dp))

                //todo volume, shuffle, preference row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DefaultToggleButton(isToggled = toggleMode == ToggleMode.SHUFFLE, onToggle = { isToggled ->
                        toggleMode = if (isToggled) ToggleMode.SHUFFLE else ToggleMode.NONE
                    }, tooltipText = "Shuffle", onClick = {/*todo onToggleShuffle*/ }) { isToggled ->
                        Icon(
                            imageVector = shuffleIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                            contentDescription = "Shuffle",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultToggleButton(isToggled = toggleMode == ToggleMode.PREFERENCES, onToggle = { isToggled ->
                        toggleMode = if (isToggled) ToggleMode.PREFERENCES else ToggleMode.NONE
                    }, tooltipText = "Preferences", onClick = {/*todo onPreferences*/ }) { isToggled ->
                        Icon(
                            imageVector = preferencesIcon(strokeColor = if (isToggled) AppColors.background else AppColors.white),
                            contentDescription = "Preferences",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    DefaultButton(
                        tooltipText = "To blacklist", onClick = {/*todo onBlacklist*/ }) {
                        Icon(
                            imageVector = blacklistIcon(),
                            contentDescription = "To blacklist",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                /*//todo track position slider, time labels
                val durationMs = /*track?.durationMs ?: 0L*/500L
                val sliderPosition = if (durationMs == 0L) 0f else /*currentTimeMs*/350L / durationMs.toFloat()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                    ) {
                    Text(formatTime(/*currentTimeMs*/350L), color = AppColors.white, fontSize = 12.sp)
                    Slider(
                        value = sliderPosition.coerceIn(0f, 1f),
                        onValueChange = {}/*onSeek*/,
                        modifier = Modifier.weight(1f)
                    )
                    Text(formatTime(durationMs), color = AppColors.white, fontSize = 12.sp)
                }*/

                /*//todo playback controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ControlButton(icon = Icons.Filled.ShoppingCart, description = "Previous", onClick = {}/*onPrevious*/)
                    Spacer(Modifier.width(16.dp))
                    ControlButton(
                        icon = if (/*isPlaying*/true) Icons.Filled.Person else Icons.Filled.PlayArrow,
                        description = "Play/Pause",
                        onClick = {}/*onPlayPause*/
                    )
                    Spacer(Modifier.width(16.dp))
                    ControlButton(icon = Icons.Filled.Refresh, description = "Next", onClick = {}/*onNext*/)
                }*/
            }
        }

        Spacer(Modifier.height(24.dp))

        /*//todo volume slider
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
        }*/
    }
}

/*@Composable
private fun ControlButton(
    icon: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(icon, description, tint = AppColors.white, modifier = Modifier.size(32.dp))
    }
}*/

/*@Composable
private fun rememberInfiniteRotation(active: Boolean): State<Float> {
    val transition = rememberInfiniteTransition(label = "vinyl")
    return if (active) {
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(8000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "angle"
        )
    } else remember { mutableStateOf(0f) }
}*/

/*private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val totalSeconds = (ms / 1000f).roundToInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}*/

/*
data class Track(
    val title: String,
    val artist: String,
    val durationMs: Long,
    val cover: ImageBitmap? = null
)*/
