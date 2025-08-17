package com.njpg.emp.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class CardPosition(
    val id: String,
    val x: Float,
    val y: Float
)

private val saveFile = File("card_positions.json")

fun saveAllPositions(positions: List<CardPosition>) {
    saveFile.writeText(Json.encodeToString(ListSerializer(CardPosition.serializer()), positions))
}

fun loadAllPositions(): List<CardPosition> {
    return if (saveFile.exists()) {
        Json.decodeFromString(ListSerializer(CardPosition.serializer()), saveFile.readText())
    } else emptyList()
}