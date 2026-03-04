package com.messenger.jaber.settings.domain.repositories

interface ClearLocalTokenRepository {
    suspend fun clearLocalToken()
}