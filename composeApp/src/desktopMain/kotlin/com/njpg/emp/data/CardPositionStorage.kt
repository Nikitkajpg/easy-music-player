package com.njpg.emp.data

import com.njpg.emp.core.CardPosition
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Объект для хранения и восстановления позиций карточек в JSON-файле.
 *
 * Используется для сохранения текущего положения всех [CardPosition] на экране
 * и последующей загрузки при перезапуске приложения.
 *
 * Сохраняет данные в файл "card_positions.json" в читаемом формате (pretty print).
 * При ошибках чтения или записи выводит сообщения в консоль.
 */
object CardPositionStorage {
    private val saveFile = File("card_positions.json")
    private val json = Json { prettyPrint = true }

    fun saveAll(positions: List<CardPosition>) {
        runCatching {
            saveFile.writeText(json.encodeToString(ListSerializer(CardPosition.serializer()), positions))
        }.onFailure {
            println("Ошибка при сохранении файла: ${it.message}")
        }
    }

    /**
     * Загружает все позиции карточек из JSON-файла.
     *
     * Если файл не существует или пустой, возвращает пустой список.
     * В случае ошибки чтения выводит сообщение в консоль и возвращает пустой список.
     *
     * @return [List] объектов [CardPosition] с сохранёнными позициями.
     */
    fun loadAll(): List<CardPosition> = runCatching {
        val content = saveFile.takeIf { it.exists() }?.readText().orEmpty().ifBlank { "[]" }
        json.decodeFromString(ListSerializer(CardPosition.serializer()), content)
    }.getOrElse {
        println("Ошибка при чтении файла: ${it.message}")
        emptyList()
    }
}