package com.messenger.jaber.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object InitRoute : Route

@Serializable
data object SignInRoute : Route

@Serializable
data object SignUpRoute : Route

@Serializable
data object MainRoute : Route

@Serializable
data object CongratsRoute : Route

@Serializable
data object ChatsRoute : Route

@Serializable
data object ProfileRoute : Route

@Serializable
data object SettingsRoute : Route

@Serializable
data object ChatRoute : Route