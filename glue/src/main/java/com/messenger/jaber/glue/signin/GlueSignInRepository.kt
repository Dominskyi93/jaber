package com.messenger.jaber.glue.signin

import com.messenger.jaber.data.SignInDataRepository
import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.Token
import com.messenger.jaber.signin.domain.repositories.SignInRepository
import javax.inject.Inject

class GlueSignInRepository @Inject constructor(
    private val signInDataRepository: SignInDataRepository
) : SignInRepository {

    override suspend fun signIn(credentials: Credentials): Token {
        return signInDataRepository.signIn(credentials)
    }

}