package com.ralphmarondev.generate_executable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform