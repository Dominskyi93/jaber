package com.messenger.jaber.domain.repositories

interface AuthRepository {
    suspend fun isAuthorized(): Boolean
}
