package com.messenger.jaber.signin.domain

import com.messenger.jaber.signin.domain.entities.Credentials

interface SignInUseCase {

    /**
     * throws InvalidCredentialsException
     * throws EmptyFieldException
     */
    suspend operator fun invoke(credentials: Credentials): Result<Unit>
}