package com.messenger.jaber.core.navigation.dsl

sealed class ScreenBackHandler {

    data object Default : ScreenBackHandler()

    data class Custom(
        val onBackPressed: () -> Unit
    ) : ScreenBackHandler()
}