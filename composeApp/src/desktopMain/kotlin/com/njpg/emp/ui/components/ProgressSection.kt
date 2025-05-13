package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.njpg.emp.ui.util.AppColors
import kotlin.math.roundToInt

@Composable
fun ProgressSection(
    currentTimeMs: Long,
    durationMs: Long,
    onSeek: (Float) -> Unit,
) {
    val sliderPosition = if (durationMs == 0L) 0f else currentTimeMs / durationMs.toFloat()

    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
    ) {
        Text(formatTime(currentTimeMs), color = AppColors.white, fontSize = 12.sp)
        Slider(
            value = sliderPosition.coerceIn(0f, 1f), onValueChange = onSeek, modifier = Modifier.weight(1f)
        )
        Text(formatTime(durationMs), color = AppColors.white, fontSize = 12.sp)
    }
}

private fun formatTime(ms: Long): String {
    if (ms <= 0) return "00:00"
    val totalSeconds = (ms / 1000f).roundToInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}