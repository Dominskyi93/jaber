package com.messenger.jaber.signin.domain.repositories

import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.Token

interface SignInRepository {
    suspend fun signIn(credentials: Credentials): Token
}
