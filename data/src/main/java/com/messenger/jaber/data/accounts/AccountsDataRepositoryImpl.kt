package com.messenger.jaber.data.accounts

import com.elveum.container.mapException
import com.elveum.container.unwrap
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.messenger.core.essentials.exceptions.ConnectionException
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
            firebaseAuthRepository
                .signIn(credentials)
                .getOrThrow()
        }.mapException(Exception::class) { e ->
            mapFirebaseException(e)
        }.unwrap()
    }

    private fun mapFirebaseException(e: Exception): Exception {
        val cause = e.cause
        return when (cause) {
            is FirebaseAuthInvalidCredentialsException -> {
                InvalidCredentialsException()
            }

            is FirebaseNetworkException -> {
                ConnectionException()
            }

            else -> {
                e
            }
        }
    }
}