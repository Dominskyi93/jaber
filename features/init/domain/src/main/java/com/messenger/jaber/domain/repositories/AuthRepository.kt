package com.messenger.jaber.domain.repositories

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

//todo use interface
@Singleton
class AuthRepository @Inject constructor() {
    suspend fun isAuthorized(): Boolean {
        delay(1000)
        return false
    }
}
