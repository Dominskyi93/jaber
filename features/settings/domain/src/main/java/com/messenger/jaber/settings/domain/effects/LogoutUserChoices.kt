package com.messenger.jaber.settings.domain.effects

interface LogoutUserChoices {
    suspend fun confirmLogout(): Boolean
}
