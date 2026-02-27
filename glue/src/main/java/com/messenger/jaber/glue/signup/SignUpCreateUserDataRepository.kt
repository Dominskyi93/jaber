package com.messenger.jaber.glue.signup

import com.messenger.jaber.data.CreateUserInfoDataRepository
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.repositories.CreateUserRepository
import com.messenger.jaber.glue.signup.mappers.MappedUserData
import javax.inject.Inject

class SignUpCreateUserDataRepository @Inject constructor(
    private val createUserInfoDataRepository: CreateUserInfoDataRepository
) : CreateUserRepository {
    override suspend fun createUserData(account: NewAccount, uid: String) {
        val userInfoRequest = MappedUserData().mapAccountToUserInfoRequest(account).copy(uid = uid)
        createUserInfoDataRepository.createUserInfo(userInfoRequest)
    }
}