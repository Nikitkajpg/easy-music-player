package com.njpg.emp.data

object DirectoryManager {
    private var _defaultFolderPath = ""

    val defaultFolderPath: String
        get() = _defaultFolderPath

    fun init(defaultFolderPath: String) {
        _defaultFolderPath = defaultFolderPath
    }

    fun updateDefaultFolderPath(newPath: String) {
        _defaultFolderPath = newPath
    }
}