package ui.components.window.controls

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState

@Composable
fun MaximizeButton(state: WindowState) {
    val isMaximized = state.placement == WindowPlacement.Maximized

    Button(
        modifier = Modifier.size(40.dp),
        onClick = {
            state.placement = if (isMaximized) {
                WindowPlacement.Floating
            } else {
                WindowPlacement.Maximized
            }
        }
    ) {
        Text("O", color = Color.White)
    }
}