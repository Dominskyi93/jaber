package com.messenger.jaber.glue.init

import com.messenger.jaber.data.SessionProvider
import com.messenger.jaber.data.session.entities.Token
import com.messenger.jaber.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class InitAuthRepository @Inject constructor(
    private val sessionProvider: SessionProvider
) : AuthRepository {
    override suspend fun isAuthorized(): Boolean {
        return sessionProvider.getToken()
            .map { it !is Token.Empty }
            .first()
    }
}