package com.njpg.emp.data

import com.njpg.emp.core.Track
import javazoom.jlgui.basicplayer.BasicPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

enum class PlayerState {
    PLAYING, PAUSED, STOPPED
}

object PlayerController {
    private val player = BasicPlayer()

    private val _playerState = MutableStateFlow(PlayerState.STOPPED)
    val playerState: StateFlow<PlayerState> = _playerState

    private var currentTrack: Track? = null

    fun togglePlayPause(track: Track) {
        when (_playerState.value) {
            PlayerState.PLAYING -> if (track.filePath == currentTrack?.filePath) pause() else play(track)
            PlayerState.PAUSED -> if (track.filePath == currentTrack?.filePath) resume() else play(track)
            PlayerState.STOPPED -> play(track)
        }
    }

    private fun play(track: Track) {
        try {
            val file = File(track.filePath)
            if (file.exists()) {
                player.open(file)
                player.play()
                currentTrack = track
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
            currentTrack = null
            _playerState.value = PlayerState.STOPPED
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}