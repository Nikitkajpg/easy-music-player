package ui.components.window.controls

import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.system.exitProcess

@Composable
fun ExitButton() {
    Button(
        modifier = Modifier.size(40.dp),
        onClick = { exitProcess(0) },
    ) {
        Text("x", color = Color.White)
    }
}