package ui.components.window.controls

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.WindowState

@Composable
fun MinimizeButton(state: WindowState) {
    Button(onClick = {
        state.isMinimized = !state.isMinimized
    }) {
        Text("_", color = Color.White)
    }
}