package com.messenger.jaber.data

import com.messenger.jaber.signin.domain.entities.Token
import com.messenger.jaber.signin.domain.entities.Credentials

interface SignInDataRepository {
    suspend fun signIn(credentials: Credentials): Token
}