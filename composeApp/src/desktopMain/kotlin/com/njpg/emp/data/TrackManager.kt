package com.njpg.emp.data

import com.njpg.emp.core.Track
import java.io.File

object TrackManager {
    private var _tracks: MutableList<Track> = mutableListOf()
    private var _tracksForDefaultPlaylist: MutableList<Track> = mutableListOf()

    val tracks: List<Track>
        get() = _tracks

    fun loadTracksForDefaultPlaylist(): List<Track> {
        _tracksForDefaultPlaylist.clear()

        val dir = File(DirectoryManager.defaultFolderPath)
        if (dir.exists() && dir.isDirectory) {
            dir.listFiles()?.forEach { file ->
                if (file.extension.equals("mp3", ignoreCase = true) || file.extension.equals(
                        "wav", ignoreCase = true
                    )
                ) {
                    val track = Track(
                        id = 0, //todo generate unique id
                        title = file.nameWithoutExtension,
                        filePath = file.absolutePath,
                        artist = "Unknown", //todo reading metadata
                        duration = 0
                    )
                    _tracksForDefaultPlaylist.add(track)
                }
            }
        }
        return _tracksForDefaultPlaylist
    }
}