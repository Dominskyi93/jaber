package com.messenger.jaber.data.accounts

import com.elveum.container.mapException
import com.elveum.container.unwrap
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.data.FirebaseAuthDataRepository
import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import com.messenger.jaber.signin.domain.exceptions.InvalidCredentialsException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AccountsDataRepositoryImpl @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthDataRepository
) : AccountsDataRepository {
    override suspend fun signIn(
        credentials: AuthDataCredentials
    ) {
        return containerOf {
            firebaseAuthRepository.signIn(credentials).getOrThrow()
        }.mapException(Exception::class) {
            InvalidCredentialsException()
        }.unwrap()
    }
}