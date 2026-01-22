package com.messenger.jaber.features.signup.domain.repositories

import com.messenger.jaber.features.signup.domain.entities.NewAccount

interface CreateUserRepository {
    suspend fun createUserData(account: NewAccount, uid: String)
}
