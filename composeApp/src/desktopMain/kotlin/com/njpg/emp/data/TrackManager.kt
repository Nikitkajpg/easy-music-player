package com.njpg.emp.data

import com.njpg.emp.core.Track
import java.io.File

object TrackManager {
    private var _tracks: MutableList<Track> = mutableListOf()
    private var _tracksForDefaultPlaylist: MutableList<Track> = mutableListOf()

    val tracks: List<Track>
        get() = _tracks

    fun getTracksForDefaultPlaylist(): List<Track> {
        val dir = File(DirectoryManager.defaultFolderPath)
        if (dir.exists() && dir.isDirectory) {
            dir.listFiles()?.forEach { file ->
                val track = Track(
                    id = 0, title = file.nameWithoutExtension, filePath = file.absolutePath, artist = "", duration = 0
                )
                _tracksForDefaultPlaylist.add(track)
            }
        }
        return _tracksForDefaultPlaylist
    }
}