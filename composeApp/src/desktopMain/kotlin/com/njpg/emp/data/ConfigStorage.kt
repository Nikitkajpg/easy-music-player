package com.njpg.emp.data

import com.njpg.emp.core.Config
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Объект для хранения и восстановления конфигурации приложения в JSON-файле.
 */
object ConfigStorage {
    private val saveFile = File("config.json")
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    /**
     * Сохраняет текущую конфигурацию в файл.
     * @param config объект Config для сохранения.
     */
    fun save(config: Config) {
        runCatching {
            saveFile.writeText(json.encodeToString(Config.serializer(), config))
        }.onFailure {
            println("Ошибка при сохранении файла конфигурации: ${it.message}")
        }
    }

    /**
     * Загружает конфигурацию из JSON-файла.
     * Если файл не существует или пуст, возвращает объект с дефолтными значениями.
     * @return объект Config с загруженными или дефолтными значениями.
     */
    fun load(): Config = runCatching {
        val content = saveFile.takeIf { it.exists() }?.readText().orEmpty().ifBlank { "[]" }
        json.decodeFromString(Config.serializer(), content)
    }.getOrElse {
        println("Ошибка при чтении файла конфигурации: ${it.message}")
        Config() // Возвращаем дефолтные значения в случае ошибки
    }
}