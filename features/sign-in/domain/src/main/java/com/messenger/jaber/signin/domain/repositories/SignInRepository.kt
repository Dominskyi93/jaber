package com.messenger.jaber.signin.domain.repositories

import com.messenger.jaber.signin.domain.entities.Credentials

interface SignInRepository {
    suspend fun signIn(credentials: Credentials)
}
