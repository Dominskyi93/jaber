package com.messenger.jaber.features.signup.domain.repositories

import com.messenger.jaber.features.signup.domain.entities.NewAccount

interface CreateAccountRepository {
    suspend fun createAccount(newAccount: NewAccount)

}