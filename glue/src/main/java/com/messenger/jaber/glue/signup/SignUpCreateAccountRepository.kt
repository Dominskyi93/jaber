package com.messenger.jaber.glue.signup

import com.messenger.jaber.data.CreateAccountDataRepository
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.repositories.CreateAccountRepository
import javax.inject.Inject

class SignUpCreateAccountRepository @Inject constructor(
    private val createAccountRepository: CreateAccountDataRepository
) : CreateAccountRepository {
    override suspend fun createAccount(newAccount: NewAccount) {
        createAccountRepository.createAccount(newAccount.login, newAccount.password)
    }
}