package com.messenger.jaber.signin.domain.usecases

import com.messenger.jaber.signin.domain.SignInUseCase
import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.validate
import com.messenger.jaber.signin.domain.repositories.SignInRepository
import javax.inject.Inject

internal class SignInUseCaseImpl @Inject constructor(
    private val signInRepository: SignInRepository
) : SignInUseCase {

    override suspend fun invoke(credentials: Credentials) {
        credentials.validate()
       return signInRepository.signIn(credentials)
    }
}