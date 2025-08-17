package com.njpg.emp.core

import kotlinx.serialization.Serializable

@Serializable
data class CardPosition(
    val id: String,
    val x: Float,
    val y: Float
)