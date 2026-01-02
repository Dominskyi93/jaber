package com.messenger.jaber.glue.signin

import com.messenger.jaber.data.LocalTokenDataRepository
import com.messenger.jaber.signin.domain.entities.Token
import com.messenger.jaber.signin.domain.repositories.LocalTokenRepository
import javax.inject.Inject

class SignInLocalTokenRepository @Inject constructor(
    private val localTokenDataRepository: LocalTokenDataRepository
) : LocalTokenRepository {
    override suspend fun saveToken(token: Token) {
        localTokenDataRepository.saveToken(token)
    }
}