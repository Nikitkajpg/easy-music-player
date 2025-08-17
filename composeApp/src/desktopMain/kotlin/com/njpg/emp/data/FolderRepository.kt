package com.njpg.emp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.njpg.emp.core.Folder

object FolderController {
    val folders: SnapshotStateList<Folder> = mutableStateListOf()

    fun load() {
        folders.clear()
        folders.addAll(FolderStorage.load())
    }

    fun add(path: String) {
        if (folders.none() { it.path == path}) {
            folders.add(Folder(0, path))
            FolderStorage.save(folders.toList())
        }
    }

    fun remove(id: Int) {
        folders.removeAll { it.id == id}
        FolderStorage.save(folders.toList())
    }

    fun save() {
        FolderStorage.save(folders.toList())
    }
}