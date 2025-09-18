package com.njpg.emp.data

import com.njpg.emp.core.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.builtins.ListSerializer
import java.io.File

object CardPositionStorage {
    private val dataDir = File("data")
    private val file = File(dataDir, "card_positions.json")

    private val defaultCardsJson = """
        [
            { "id": "MAIN", "x": 0.0, "y": 0.0, "width": 200, "height": 200 },
            { "id": "INFO", "x": 100.0, "y": 100.0, "width": 200, "height": 200 },
            { "id": "DEFAULT_PLAYLIST", "x": 300.0, "y": 300.0, "width": 200, "height": 200 }
        ]
    """.trimIndent()

    suspend fun saveAll(positions: List<Card>) {
        withContext(Dispatchers.IO) {
            runCatching {
                if (!dataDir.exists()) dataDir.mkdirs()
                file.writeText(AppJson.encodeToString(ListSerializer(Card.serializer()), positions))
            }.onFailure {
                println("Error while saving the positions file: ${it.message}")
            }
        }
    }

    suspend fun loadAll(): List<Card> = withContext(Dispatchers.IO) {
        runCatching {
            if (!file.exists()) {
                file.apply {
                    parentFile.mkdirs()
                    createNewFile()
                    writeText(defaultCardsJson)
                }
            }
            val content = file.readText().ifBlank { defaultCardsJson }
            AppJson.decodeFromString(ListSerializer(Card.serializer()), content)
        }.getOrElse {
            println("Error while reading positions file: ${it.message}")
            runCatching {
                AppJson.decodeFromString(ListSerializer(Card.serializer()), defaultCardsJson)
            }.getOrDefault(emptyList())
        }
    }
}