package com.njpg.emp.data

import com.njpg.emp.core.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object ConfigStorage {
    private val dataDir = File("data")
    private val saveFile = File(dataDir, "config.json")

    suspend fun save(config: Config) {
        withContext(Dispatchers.IO) {
            runCatching {
                if (!dataDir.exists()) dataDir.mkdirs()
                saveFile.writeText(AppJson.encodeToString(Config.serializer(), config))
            }.onFailure {
                println("Error while saving config file: ${it.message}")
            }
        }
    }

    suspend fun load(): Config = withContext(Dispatchers.IO) {
        runCatching {
            if (!dataDir.exists()) dataDir.mkdirs()
            val content = saveFile.takeIf { it.exists() }?.readText()
            if (content.isNullOrBlank()) {
                Config()
            } else {
                AppJson.decodeFromString(Config.serializer(), content)
            }
        }.getOrElse {
            println("Error while reading config file: ${it.message}")
            Config()
        }
    }
}