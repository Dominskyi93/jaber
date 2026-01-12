package com.messenger.jaber.data.createAccount

import com.messenger.jaber.data.LoginAvailabilityDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginAvailabilityDataRepositoryImpl @Inject constructor() : LoginAvailabilityDataRepository {
    override suspend fun isLoginAvailable(): Boolean {
        //todo
        return true
    }
}