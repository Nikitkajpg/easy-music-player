package com.njpg.emp.ui.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.njpg.emp.data.DirectoryManager
import com.njpg.emp.data.Localization
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.icons.*
import com.njpg.emp.ui.theme.AppTheme
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView

@Composable
fun MainCard() {
    var isPlaying by remember { mutableStateOf(false) }
    val selectedFolderPath = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Row {
            DefaultButton(
                tooltipText = Localization.tr("previous_track"), onClick = {}) {
                Icon(
                    imageVector = previousIcon(),
                    contentDescription = "Previous track",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
            DefaultButton(
                tooltipText = if (isPlaying) Localization.tr("pause") else Localization.tr("play"), onClick = {
                    isPlaying = !isPlaying
                }) {
                Icon(
                    imageVector = if (isPlaying) pauseIcon() else playIcon(),
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
            DefaultButton(
                tooltipText = Localization.tr("next_track"), onClick = {}) {
                Icon(
                    imageVector = nextIcon(),
                    contentDescription = "Next track",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
            DefaultButton(
                tooltipText = Localization.tr("select_folder"), onClick = {
                    val fileChooser = JFileChooser(
                        if (DirectoryManager.defaultFolderPath == "home") FileSystemView.getFileSystemView().homeDirectory else File(
                            DirectoryManager.defaultFolderPath
                        )
                    )
                    fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
                    fileChooser.dialogTitle = Localization.tr("choose_dir")

                    val result = fileChooser.showOpenDialog(null)

                    if (result == JFileChooser.APPROVE_OPTION) {
                        val selectedFile: File = fileChooser.selectedFile
                        selectedFolderPath.value = selectedFile.absolutePath
                        DirectoryManager.updateDefaultFolderPath(selectedFile.absolutePath)
                    }
                }) {
                Icon(
                    imageVector = selectFolderIcon(AppTheme.colors.toggled),
                    contentDescription = "Select folder",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}