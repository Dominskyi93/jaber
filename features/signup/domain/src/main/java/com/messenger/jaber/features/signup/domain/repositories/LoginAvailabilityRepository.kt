package com.messenger.jaber.features.signup.domain.repositories

interface LoginAvailabilityRepository {
    suspend fun isLoginAvailable(login: String): Boolean
}