package com.messenger.jaber.data

import com.messenger.jaber.data.session.entities.Token
import kotlinx.coroutines.flow.Flow

interface SessionProvider {

    fun getToken(): Flow<Token>
}