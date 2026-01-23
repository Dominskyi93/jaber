package com.messenger.jaber.glue.signup

import com.messenger.jaber.data.CreateUserDataRepository
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.repositories.CreateUserRepository
import com.messenger.jaber.glue.signup.mappers.MappedUserData
import javax.inject.Inject

class SignUpCreateUserDataRepository @Inject constructor(
    private val createUserDataRepository: CreateUserDataRepository
) : CreateUserRepository {
    override suspend fun createUserData(account: NewAccount, uid: String) {
        val userData = MappedUserData().mapAccountToUserData(account).copy(uid = uid)
        createUserDataRepository.createUser(userData)
    }
}