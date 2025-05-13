package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.AppColors

@Composable
fun VolumeSection(
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
            value = volume.coerceIn(0f, 1f), onValueChange = onVolumeChange, modifier = Modifier.width(200.dp)
        )
    }
}