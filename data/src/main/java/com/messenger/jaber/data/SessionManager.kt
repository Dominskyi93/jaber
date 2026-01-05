package com.messenger.jaber.data

import com.messenger.jaber.data.session.entities.AuthDataToken

interface SessionManager : SessionProvider {
    suspend fun saveToken(token: AuthDataToken)
}