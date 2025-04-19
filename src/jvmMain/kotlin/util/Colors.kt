package util

import androidx.compose.ui.graphics.Color

fun String.toColor(): Long {
    val hex = this.removePrefix("#")

    val colorLong = when (hex.length) {
        6 -> "FF$hex".toLong(16)
        8 -> hex.toLong(16)
        else -> throw IllegalArgumentException("Invalid hex color: $this")
    }

    return colorLong
}

val colorOfBackground = Color("#222222".toColor())
val colorNotWhite = Color("#F4FFF8".toColor())
val colorGray = Color("#444444".toColor())
val colorLightGray = Color("#333333".toColor())
val colorRed = Color("#FF3D00".toColor())
val colorLightRed = Color("#FF6E40".toColor())
val colorYellow = Color("#FFC107".toColor())
val colorLightYellow = Color("#FFD54F".toColor())
val colorDarkYellow = Color("#B89000".toColor())