package com.njpg.emp.core

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import emp.composeapp.generated.resources.Res
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.io.File

@Serializable
data class Translations(
    val en: Map<String, String> = emptyMap(), val ru: Map<String, String> = emptyMap()
)

/**
 * Объект-синглтон, предоставляющий централизованный и реактивный механизм для управления
 * локализацией в Compose-приложении.
 *
 * Позволяет загружать строковые ресурсы из встроенного файла и перезаписывать их
 * с помощью внешнего файла `strings.json`, обеспечивая гибкую систему перевода.
 */
object Localization {
    private val json = Json { ignoreUnknownKeys = true }
    private var translations: Translations = Translations()
    private var _currentLanguage by mutableStateOf("en")
    private var currentLanguage: String
        get() = _currentLanguage
        set(value) {
            _currentLanguage = value
        }

    private val externalFile = File("strings.json") // внешний файл рядом с приложением

    @OptIn(ExperimentalResourceApi::class)
    suspend fun init() {
        // Загружаем встроенный JSON
        val defaultJson = Res.readBytes("files/strings.json").decodeToString()
        translations = json.decodeFromString(Translations.serializer(), defaultJson)

        // Если есть внешний JSON → мержим
        if (externalFile.exists()) {
            runCatching {
                val overrideJson = externalFile.readText()
                val override = json.decodeFromString(Translations.serializer(), overrideJson)

                translations = translations.copy(
                    en = translations.en + override.en, ru = translations.ru + override.ru
                )
            }
        }

        setLanguage("en")
    }

    private fun setLanguage(lang: String) {
        currentLanguage = lang
    }

    fun toggleLanguage() {
        currentLanguage = if (currentLanguage == "en") "ru" else "en"
    }

    fun tr(key: String): String {
        return when (currentLanguage) {
            "ru" -> translations.ru[key]
            else -> translations.en[key]
        } ?: key
    }

    fun getCurrentLang(): String = currentLanguage.uppercase()
}