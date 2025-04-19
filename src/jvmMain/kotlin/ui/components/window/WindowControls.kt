package ui.components.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import ui.components.window.controls.TitleBarButton
import ui.components.window.controls.TitleIcon
import ui.icons.CustomCloseIcon
import ui.icons.CustomMaximizeIcon
import ui.icons.CustomMinimizeIcon
import util.colorLightRed
import util.colorOfBackground
import util.colorRed
import kotlin.system.exitProcess

@Composable
fun WindowControls(state: WindowState) {
    val isMaximized = state.placement == WindowPlacement.Maximized
    val closeIcon: Painter = rememberVectorPainter(CustomCloseIcon)
    val minimizeIcon: Painter = rememberVectorPainter(CustomMinimizeIcon)
    val maximizeIcon: Painter = rememberVectorPainter(CustomMaximizeIcon)

    Row(
        modifier = Modifier.fillMaxWidth().height(48.dp).background(colorOfBackground).padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TitleIcon()
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TitleBarButton(
                icon = minimizeIcon,
                onClick = { state.isMinimized = !state.isMinimized },
                contentDescription = "Minimize"
            )

            TitleBarButton(
                icon = maximizeIcon, onClick = {
                    state.placement = if (isMaximized) {
                        WindowPlacement.Floating
                    } else {
                        WindowPlacement.Maximized
                    }
                }, contentDescription = "Maximize"
            )
            TitleBarButton(
                icon = closeIcon,
                onClick = { exitProcess(0) },
                contentDescription = "Exit",
                normalBorder = colorRed,
                hoverBorder = colorLightRed
            )
        }
    }
}
