package com.messenger.jaber.data.accounts

import com.elveum.container.map
import com.elveum.container.mapException
import com.elveum.container.unwrap
import com.messenger.core.essentials.exceptions.BackendException
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.data.accounts.exceptions.InvalidCredentialsDataException
import com.messenger.jaber.data.accounts.remote.AccountsApi
import com.messenger.jaber.data.accounts.remote.dto.SignInRequestDto
import com.messenger.jaber.data.session.entities.AuthDataToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AccountsDataRepositoryImpl @Inject constructor(
    private val accountsApi: AccountsApi
) : AccountsDataRepository {
    override suspend fun signIn(
        credentials: AuthDataCredentials
    ): AuthDataToken.Default {
        val request = SignInRequestDto(
            user = credentials.login,
            password = credentials.password
        )
        return containerOf { accountsApi.signIn(request) }
            .map {
                AuthDataToken.Default(accessToken = it.accessToken)
            }
            .mapException(BackendException::class) {
                if (it.httpCode == 403) {
                    throw InvalidCredentialsDataException(it)
                } else {
                    it
                }
            }.unwrap()
    }
}