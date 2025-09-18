package com.njpg.emp.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.njpg.emp.core.CardId
import com.njpg.emp.data.CardManager
import com.njpg.emp.ui.components.cards.DefaultPlaylistCard
import com.njpg.emp.ui.components.cards.MainCard

@Composable
fun CardContainer() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current
        val containerWidthPx = with(density) { maxWidth.toPx() }
        val containerHeightPx = with(density) { maxHeight.toPx() }

        CardManager.cards.forEach { card ->
            DraggableCard(
                card = card,
                containerWidthPx = containerWidthPx,
                containerHeightPx = containerHeightPx,
                onDragEnd = { updatedCard ->
                    CardManager.update(updatedCard)
                },
                onResizeEnd = { updatedCard ->
                    CardManager.update(updatedCard)
                }) {
                when (card.id) {
                    CardId.MAIN -> {
                        MainCard()
                    }

                    CardId.INFO -> {
                        Text(
                            text = "Content for\n${card.id}", modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    CardId.DEFAULT_PLAYLIST -> {
                        DefaultPlaylistCard()
                    }
                }

            }
        }
    }
}