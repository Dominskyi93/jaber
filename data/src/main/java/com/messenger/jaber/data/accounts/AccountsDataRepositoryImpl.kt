package com.messenger.jaber.data.accounts

import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.data.accounts.remote.AccountsApi
import com.messenger.jaber.data.session.entities.AuthDataToken

internal class AccountsDataRepositoryImpl @Inject constructor(
    private val accountsApi: AccountsApi
) : AccountsDataRepository {
    override suspend fun signIn(
        credentials: AuthDataCredentials
    ): AuthDataToken.Default {

    }
}