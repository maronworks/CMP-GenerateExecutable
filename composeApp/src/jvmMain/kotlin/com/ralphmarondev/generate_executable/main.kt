package com.ralphmarondev.generate_executable

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Generate Executable",
    ) {
        App()
    }
}