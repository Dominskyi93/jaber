package com.messenger.jaber.data.localToken

import com.messenger.jaber.data.LocalTokenDataRepository
import com.messenger.jaber.signin.domain.entities.Token
import javax.inject.Inject

class LocalTokenDataRepositoryImpl @Inject constructor(
) : LocalTokenDataRepository {
    override suspend fun saveToken(token: Token) = Unit
}