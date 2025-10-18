package com.njpg.emp.data

import com.njpg.emp.core.Track
import javazoom.jlgui.basicplayer.BasicController
import javazoom.jlgui.basicplayer.BasicPlayer
import javazoom.jlgui.basicplayer.BasicPlayerEvent
import javazoom.jlgui.basicplayer.BasicPlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

enum class PlayerState {
    PLAYING, PAUSED, STOPPED
}

object PlayerController : BasicPlayerListener {
    private val player = BasicPlayer()

    private val _playerState = MutableStateFlow(PlayerState.STOPPED)
    val playerState: StateFlow<PlayerState> = _playerState

    private var _currentTrack = MutableStateFlow<Track?>(null)
    val currentTrack: StateFlow<Track?> = _currentTrack

    private var currentPlaylist: List<Track> = emptyList()
    private var currentTrackIndex: Int = -1

    init {
        player.addBasicPlayerListener(this)
    }

    override fun stateUpdated(event: BasicPlayerEvent?) {
        if (event?.code == BasicPlayerEvent.EOM) {
            CoroutineScope(Dispatchers.Default).launch {
                playNext()
            }
        }
    }

    override fun opened(stream: Any?, properties: MutableMap<Any?, Any?>?) {}
    override fun progress(
        bytesread: Int, microseconds: Long, pcmdata: ByteArray?, properties: MutableMap<Any?, Any?>?
    ) {
    }

    override fun setController(controller: BasicController?) {}

    fun togglePlayPause() {
        when (_playerState.value) {
            PlayerState.PLAYING -> pause()
            PlayerState.PAUSED -> resume()
            PlayerState.STOPPED -> {
                if (currentPlaylist.isNotEmpty()) {
                    val indexToPlay = if (currentTrackIndex == -1) 0 else currentTrackIndex
                    playTrackFromPlaylist(currentPlaylist, indexToPlay)
                }
            }
        }
    }

    fun playTrackFromPlaylist(playlist: List<Track>, index: Int) {
        if (playlist.isEmpty() || index !in playlist.indices) {
            stop()
            return
        }

        this.currentPlaylist = playlist
        this.currentTrackIndex = index

        val trackToPlay = playlist[index]
        play(trackToPlay)
    }

    private fun play(track: Track) {
        try {
            val file = File(track.filePath)
            if (file.exists()) {
                player.open(file)
                player.play()
                _currentTrack.value = track
                _playerState.value = PlayerState.PLAYING
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _playerState.value = PlayerState.STOPPED
        }
    }

    private fun pause() {
        try {
            player.pause()
            _playerState.value = PlayerState.PAUSED
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun resume() {
        try {
            player.resume()
            _playerState.value = PlayerState.PLAYING
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            player.stop()
            _currentTrack.value = null
            _playerState.value = PlayerState.STOPPED
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playNext() {
        if (currentPlaylist.isEmpty()) return

        var nexIndex = currentTrackIndex + 1
        if (nexIndex >= currentPlaylist.size) {
            nexIndex = 0
        }
        playTrackFromPlaylist(currentPlaylist, nexIndex)
    }

    fun playPrevious() {
        if (currentPlaylist.isEmpty()) return

        var prevIndex = currentTrackIndex - 1
        if (prevIndex < 0) {
            prevIndex = currentPlaylist.size - 1 // Зацикливание плейлиста
        }
        playTrackFromPlaylist(currentPlaylist, prevIndex)
    }
}