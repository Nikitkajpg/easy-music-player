package com.njpg.emp.data

import com.njpg.emp.core.Playlist

object PlaylistManager {
    private var _playlists: MutableList<Playlist> = mutableListOf()

    val playlists: List<Playlist>
        get() = _playlists

    fun init() {
        val tracks = TrackManager.loadTracksForDefaultPlaylist()
        _playlists.add(Playlist(Localization.tr("all_tracks"), tracks))
    }

    fun reloadFromDefaultPlaylist() {
        val tracks = TrackManager.loadTracksForDefaultPlaylist()
        if (_playlists.isNotEmpty()) {
            _playlists[0] = Playlist(Localization.tr("all_tracks"), tracks)
        }
    }

    fun getDefaultPlaylist(): Playlist? {
        return _playlists.getOrNull(0)
    }
}