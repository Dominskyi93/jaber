package com.messenger.jaber.data

import com.messenger.jaber.signin.domain.entities.Token

interface LocalTokenDataRepository {

    suspend fun saveToken(token: Token)
}