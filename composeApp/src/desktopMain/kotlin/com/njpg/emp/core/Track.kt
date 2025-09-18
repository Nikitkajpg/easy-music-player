package com.njpg.emp.core

data class Track(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: Long,
    val filePath: String
)
