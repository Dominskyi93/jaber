package com.messenger.jaber.core.navigation.dsl

sealed class ScreenToolbar {

    data object Hidden : ScreenToolbar()

    data class Default(
        val title: String
    ) : ScreenToolbar()


}