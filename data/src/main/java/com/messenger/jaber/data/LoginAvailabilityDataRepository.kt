package com.messenger.jaber.data

interface LoginAvailabilityDataRepository {
    suspend fun isLoginAvailable(): Boolean
}