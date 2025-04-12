package ui.components.window

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import ui.components.window.controls.ExitButton
import ui.components.window.controls.MaximizeButton
import ui.components.window.controls.MinimizeButton
import ui.components.window.controls.TitleIcon

@Composable
fun WindowControls(state: WindowState) {
    Row(
        modifier = Modifier.fillMaxWidth().height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TitleIcon()
        MinimizeButton(state)
        MaximizeButton(state)
        ExitButton()
    }
}
