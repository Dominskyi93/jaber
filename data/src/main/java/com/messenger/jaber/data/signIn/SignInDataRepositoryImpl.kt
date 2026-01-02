package com.messenger.jaber.data.signIn

import com.messenger.jaber.data.SignInDataRepository
import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.Token
import com.messenger.jaber.signin.domain.exceptions.InvalidCredentialsException
import kotlinx.coroutines.delay
import javax.inject.Inject

class SignInDataRepositoryImpl @Inject constructor() : SignInDataRepository {
    override suspend fun signIn(credentials: Credentials): Token {
        delay(1000)
        if (credentials.login == "admin" && credentials.password == "1234") {
            return FakeToken()
        } else {
            throw InvalidCredentialsException()
        }
    }
}

class FakeToken : Token