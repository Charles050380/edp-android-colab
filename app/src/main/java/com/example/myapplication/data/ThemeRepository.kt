package com.example.myapplication.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ThemeRepository {
    private val _isDarkTheme = MutableStateFlow(false)

    fun observeDarkTheme(
        scope: CoroutineScope,
        started: SharingStarted,
        initialValue: Boolean
    ): StateFlow<Boolean> {
        return _isDarkTheme.stateIn(scope, started, initialValue)
    }

    suspend fun setDarkTheme(dark: Boolean) {
        _isDarkTheme.value = dark
    }
}
