package com.njpg.emp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import com.njpg.emp.ui.util.PlayState
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.vinylPlayerStick
import org.jetbrains.compose.resources.painterResource

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