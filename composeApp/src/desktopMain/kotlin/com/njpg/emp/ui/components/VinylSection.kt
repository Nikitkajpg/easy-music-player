package com.njpg.emp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.njpg.emp.ui.util.PlayState
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.vinylPlayer
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