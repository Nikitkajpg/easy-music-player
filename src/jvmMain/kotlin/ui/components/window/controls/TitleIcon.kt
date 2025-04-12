package ui.components.window.controls

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TitleIcon() {
    Icon(
        painter = painterResource("icons/emp_png48.png"),
        contentDescription = "EMP Icon",
        tint = Color.Unspecified,
        modifier = Modifier.size(24.dp)
    )
}