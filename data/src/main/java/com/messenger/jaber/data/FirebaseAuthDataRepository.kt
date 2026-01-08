package com.messenger.jaber.data

import com.google.firebase.auth.AuthResult
import com.messenger.jaber.data.accounts.entities.AuthDataCredentials

interface FirebaseAuthDataRepository {

    suspend fun signIn(credentials: AuthDataCredentials): Result<Unit>
}