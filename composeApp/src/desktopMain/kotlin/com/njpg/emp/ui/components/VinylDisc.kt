package com.njpg.emp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.Dp
import com.njpg.emp.core.PlayState
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.vinyl
import org.jetbrains.compose.resources.painterResource

@Composable
fun VinylDisc(
    playState: PlayState, size: Dp, modifier: Modifier
) {
    val angle = remember { Animatable(0f) }

    LaunchedEffect(playState) {
        if (playState == PlayState.PLAYING) {
            angle.animateTo(
                targetValue = angle.value + 360f, animationSpec = infiniteRepeatable(
                    animation = tween(8000, easing = LinearEasing), repeatMode = RepeatMode.Restart
                )
            )
        } else {
            angle.stop()
        }
    }

    Box(
        modifier = modifier.size(size).rotate(angle.value)
    ) {
        Image(
            painter = painterResource(Res.drawable.vinyl), contentDescription = "Vinyl disc"
        )
    }
}