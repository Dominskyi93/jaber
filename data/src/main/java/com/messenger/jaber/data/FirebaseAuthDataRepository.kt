package com.messenger.jaber.data

import com.messenger.jaber.data.accounts.entities.AuthDataCredentials

interface FirebaseAuthDataRepository {

    suspend fun signIn(credentials: AuthDataCredentials)
}