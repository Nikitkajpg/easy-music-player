package com.njpg.emp.data

import com.njpg.emp.core.Playlist

object PlaylistManager {
    private var _playlists: MutableList<Playlist> = mutableListOf()

    val playlists: List<Playlist>
        get() = _playlists

    fun init() {
        _playlists.add(Playlist(Localization.tr("all_tracks"), TrackManager.getTracksForDefaultPlaylist()))
    }

    fun getDefaultPlaylist(): Playlist {
        return _playlists[0]
    }
}