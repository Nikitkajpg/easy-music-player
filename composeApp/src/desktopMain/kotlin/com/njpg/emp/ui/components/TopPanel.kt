package com.njpg.emp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.data.CardManager
import com.njpg.emp.ui.components.buttons.DefaultButton
import com.njpg.emp.ui.util.AppColors
import com.njpg.emp.ui.util.animatedAppColors
import com.njpg.emp.ui.util.icons.*
import emp.composeapp.generated.resources.Res
import emp.composeapp.generated.resources.icon_svg
import org.jetbrains.compose.resources.painterResource
import kotlin.system.exitProcess

/**
 * Верхняя панель окна приложения "Easy Music Player".
 *
 * Панель содержит:
 * - Левый блок: иконка приложения и название.
 * - Правый блок: кнопки управления окном (смена языка, смена темы, свернуть, развернуть/вернуть, закрыть).
 *
 * Панель полностью перетаскиваемая благодаря [WindowDraggableArea].
 *
 * При закрытии приложения сохраняются позиции карточек через [CardManager.savePositions] и вызывается [exitProcess].
 */
@Composable
fun WindowScope.TopPanel(windowState: WindowState) {
    WindowDraggableArea(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().background(animatedAppColors().background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            /**
             * Левый блок: иконка и название приложения
             */
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.icon_svg),
                    contentDescription = "Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(Modifier.width(20.dp))
                Text(
                    text = "Easy Music Player", color = animatedAppColors().yellow, fontSize = 16.sp
                )
            }

            /**
             * Правый блок: кнопки управления окном
             */
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 10.dp)
            ) {
                DefaultButton(
                    tooltipText = "Change theme", onClick = { AppColors.toggleTheme() }) {
                    Icon(
                        imageVector = blacklistIcon(),
                        contentDescription = "Change theme",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
                DefaultButton(
                    tooltipText = "Minimize", onClick = {
                        windowState.isMinimized = true
                    }) {
                    Icon(
                        imageVector = minimizeIcon(),
                        contentDescription = "Minimize",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }

                DefaultButton(
                    tooltipText = "Maximize", onClick = {
                        windowState.placement =
                            if (windowState.placement == WindowPlacement.Maximized) WindowPlacement.Floating
                            else WindowPlacement.Maximized
                    }) {
                    Icon(
                        imageVector = if (windowState.placement == WindowPlacement.Maximized) maximizeWindowIcon()
                        else maximizeFullIcon(),
                        contentDescription = "Maximize",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }

                DefaultButton(
                    tooltipText = "Exit",
                    pressedColor = animatedAppColors().redPressed,
                    hoveredColor = animatedAppColors().red,
                    onClick = {
                        CardManager.savePositions()
                        exitProcess(0)
                    }) {
                    Icon(
                        imageVector = exitIcon(),
                        contentDescription = "Exit",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}