package com.messenger.jaber.data

import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.data.accounts.exceptions.InvalidCredentialsDataException

interface AccountsDataRepository {

    /**
     * @throws InvalidCredentialsDataException
     */
    suspend fun signIn(
        credentials: AuthDataCredentials
    )
}