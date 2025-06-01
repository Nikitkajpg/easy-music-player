package com.njpg.emp.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.njpg.emp.core.PlayState
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.vinyl
import emp.composeapp.generated.resources.vinylPlayer
import emp.composeapp.generated.resources.vinylPlayerStick
import org.jetbrains.compose.resources.painterResource

@Composable
fun VinylSection(
    playState: PlayState, discSize: Dp
) {
    Box(
        modifier = Modifier.size(discSize + 60.dp).aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(Res.drawable.vinylPlayer),
            contentDescription = "Vinyl player",
            modifier = Modifier.fillMaxSize()
        )

        VinylDisc(
            playState = playState, size = discSize, modifier = Modifier.align(Alignment.Center)
        )

        VinylStick(playState)
    }
}

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

@Composable
fun VinylStick(
    playState: PlayState
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (playState == PlayState.PAUSE) -20f else 0f, animationSpec = tween(durationMillis = 1000)
    )

    Box(
        modifier = Modifier.zIndex(2f).graphicsLayer(
            rotationZ = rotationAngle, transformOrigin = TransformOrigin(0.9f, 0.1f)
        )
    ) {
        Image(
            painter = painterResource(Res.drawable.vinylPlayerStick),
            contentDescription = "Vinyl player stick",
            modifier = Modifier.fillMaxSize()
        )
    }
}