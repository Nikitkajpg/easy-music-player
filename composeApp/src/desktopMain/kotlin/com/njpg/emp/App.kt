package com.njpg.emp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.WindowState
import com.njpg.emp.core.Config
import com.njpg.emp.data.CardManager
import com.njpg.emp.data.ConfigStorage
import com.njpg.emp.data.Localization
import com.njpg.emp.ui.components.MiddlePanel
import com.njpg.emp.ui.components.TopPanel
import com.njpg.emp.ui.theme.ThemeManager
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
fun WindowScope.App(windowState: WindowState) {
    val scope = rememberCoroutineScope()

    Column {
        TopPanel(
            windowState = windowState,
            onExitRequest = {
                scope.launch {
                    CardManager.save()
                    val config = Config(
                        lang = Localization.getCurrentLang(),
                        theme = ThemeManager.currentTheme
                    )
                    ConfigStorage.save(config)
                    exitProcess(0)
                }
            }
        )
        MiddlePanel()
    }
}