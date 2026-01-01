package com.messenger.jaber.signin.domain.repositories

import com.messenger.jaber.signin.domain.entities.Token

interface LocalTokenRepository {
    suspend fun saveToken(token: Token)

}
