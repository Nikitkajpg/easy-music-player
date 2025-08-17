package com.njpg.emp.data

import com.njpg.emp.core.Folder
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object FolderStorage {
    private val folderFile: File = File(System.getProperty("user.dir"), "folders.json")

    fun load(): List<Folder> {
        return if (folderFile.exists()) {
            try {
                val text = folderFile.readText()
                Json.decodeFromString(text)
            } catch (e: Exception) {
                println("Failed to read folders: ${e.message}")
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    fun save(folders: List<Folder>) {
        try {
            folderFile.parentFile.mkdirs()
            folderFile.writeText(Json.encodeToString(folders))
        } catch (e: Exception) {
            println("Failed to save folders: ${e.message}")
        }
    }
}