package com.njpg.emp.data

import com.njpg.emp.core.Config
import kotlinx.serialization.json.Json
import java.io.File

object ConfigStorage {
    private val saveFile = File("config.json")
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }

    fun save(config: Config) {
        runCatching {
            saveFile.writeText(json.encodeToString(Config.serializer(), config))
        }.onFailure {
            println("Ошибка при сохранении файла конфигурации: ${it.message}")
        }
    }

    fun load(): Config = runCatching {
        val content = saveFile.takeIf { it.exists() }?.readText().orEmpty().ifBlank { "[]" }
        json.decodeFromString(Config.serializer(), content)
    }.getOrElse {
        println("Ошибка при чтении файла конфигурации: ${it.message}")
        Config()
    }
}