package com.njpg.emp.core

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: CardId,
    val x: Float,
    val y: Float,
    val width: Int,
    val height: Int
)