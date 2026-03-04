package com.messenger.jaber.data

import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.data.accounts.exceptions.InvalidCredentialsDataException
import com.messenger.jaber.data.session.entities.AuthDataToken

interface AccountsDataRepository {

    /**
     * @throws InvalidCredentialsDataException
     */
    suspend fun signIn(
        credentials: AuthDataCredentials
    ): AuthDataToken.Default

    suspend fun logout()
}