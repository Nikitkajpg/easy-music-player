package com.njpg.emp.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.icons.backIcon
import com.njpg.emp.ui.util.icons.exitIcon
import com.njpg.emp.ui.util.icons.okIcon
import java.io.File

@Composable
fun FolderPickerDialog(
    initialDirectory: File = File(System.getProperty("user.home")),
    onFolderSelected: (File) -> Unit,
    onDismissRequest: () -> Unit
) {
    var currentDirectory by remember { mutableStateOf(initialDirectory) }
    val subDirectories = currentDirectory.listFiles()?.filter { it.isDirectory } ?: emptyList()
    val rootDrives = File.listRoots().toList()

    DialogWindow(
        onCloseRequest = onDismissRequest,
        title = "Choose folder",
        undecorated = true,
        resizable = false,
        state = rememberDialogState(width = 600.dp, height = 400.dp)
    ) {
        Surface {
            Column(
                modifier = Modifier.fillMaxWidth().background(AppColors.background)
                    .border(width = 1.dp, color = AppColors.pressed).padding(8.dp)
            ) {
                WindowDraggableArea(modifier = Modifier.fillMaxWidth()) { //TODO: лагает перемещение окна
                    FolderPickerToolBar(
                        onBackClick = {
                            currentDirectory = currentDirectory.parentFile ?: currentDirectory
                        }, onCloseClick = onDismissRequest
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()).padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rootDrives.forEach { drive ->
                        DefaultButton(
                            tooltipText = drive.name,
                            onClick = { currentDirectory = drive },
                            modifier = Modifier.border(
                                width = 0.5.dp,
                                color = AppColors.yellow,
                                shape = RoundedCornerShape(8.dp)
                            )
                        ) {
                            Text(text = drive.path, color = AppColors.white)
                        }
                    }
                }

                Text(
                    text = "Current directory: ${currentDirectory.absolutePath}",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = AppColors.white
                )

                LazyColumn(modifier = Modifier.height(250.dp)) {
                    items(subDirectories.size) { index ->
                        val folder = subDirectories[index]
                        Box(
                            modifier = Modifier.background(
                                color = AppColors.transparent, shape = RoundedCornerShape(8.dp)
                            ).border(width = 1.dp, color = AppColors.hovered, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = folder.name,
                                modifier = Modifier.fillMaxWidth().clickable { currentDirectory = folder }
                                    .padding(8.dp),
                                color = AppColors.white
                            )
                        }
                    }
                }

                FolderPickerBottomBar(
                    onChooseClick = {
                        onFolderSelected(currentDirectory)
                        onDismissRequest()
                    })
            }
        }
    }
}

@Composable
private fun FolderPickerToolBar(
    onBackClick: () -> Unit, onCloseClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        DefaultButton(
            tooltipText = "Back", onClick = onBackClick
        ) {
            Icon(
                imageVector = backIcon(),
                contentDescription = "Back",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        DefaultButton(tooltipText = "Close", onClick = onCloseClick) {
            Icon(
                imageVector = exitIcon(strokeColor = AppColors.red),
                contentDescription = "Back",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun FolderPickerBottomBar(
    onChooseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End
    ) {
        DefaultButton(
            tooltipText = "Choose",
            onClick = onChooseClick,
            modifier = Modifier.border(width = 1.dp, color = AppColors.yellow, shape = RoundedCornerShape(8.dp))
        ) {
            Icon(
                imageVector = okIcon(strokeColor = AppColors.yellow),
                contentDescription = "Choose",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

