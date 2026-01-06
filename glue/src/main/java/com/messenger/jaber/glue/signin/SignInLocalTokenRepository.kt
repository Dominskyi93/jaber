package com.messenger.jaber.glue.signin

import com.messenger.jaber.data.SessionManager
import com.messenger.jaber.glue.signin.mappers.MappedToken
import com.messenger.jaber.signin.domain.entities.Token
import com.messenger.jaber.signin.domain.repositories.LocalTokenRepository
import javax.inject.Inject

class SignInLocalTokenRepository @Inject constructor(
    private val sessionManager: SessionManager
) : LocalTokenRepository {
    override suspend fun saveToken(token: Token) {
        (token as? MappedToken)
            ?.origin
            ?.let { sessionManager.saveToken(it) }
    }
}