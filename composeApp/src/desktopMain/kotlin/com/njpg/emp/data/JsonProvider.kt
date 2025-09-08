package com.njpg.emp.data

import kotlinx.serialization.json.Json

/**
 * Общий, повторно используемый экземпляр Json для всего приложения.
 * Обеспечивает единые настройки сериализации и экономит ресурсы.
 */
internal val AppJson = Json {
    prettyPrint = true      // Улучшает читаемость JSON файлов
    encodeDefaults = true   // Сохраняет поля со значениями по умолчанию
    ignoreUnknownKeys = true // Позволяет игнорировать неизвестные поля при чтении JSON
}