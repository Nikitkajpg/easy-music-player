package com.njpg.emp.data

import com.njpg.emp.core.Card
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

object CardPositionStorage {
    private val dataDir = File("data")
    private val file = File(dataDir, "card_positions.json")
    private val json = Json { prettyPrint = true }

    fun saveAll(positions: List<Card>) {
        runCatching {
            if (!dataDir.exists()) dataDir.mkdirs()
            file.writeText(json.encodeToString(ListSerializer(Card.serializer()), positions))
        }.onFailure {
            println("Error saving the file: ${it.message}")
        }
    }

    fun loadAll(): List<Card> = runCatching {
        if (!dataDir.exists()) dataDir.mkdirs()
        if (!file.exists()) file.createNewFile()
        val content = file.readText().ifBlank {
            """
                [
                    {
                        "id": "Card 1",
                        "x": 0.0,
                        "y": 0.0,
                        "width": 200,
                        "height": 200
                    },
                    {
                        "id": "Card 2",
                        "x": 100.0,
                        "y": 100.0,
                        "width": 200,
                        "height": 200
                    }
                ]
            """.trimIndent()
        }
        json.decodeFromString(ListSerializer(Card.serializer()), content)
    }.getOrElse {
        println("Ошибка при чтении файла: ${it.message}")
        emptyList()
    }
}