package com.messenger.jaber.glue.signup.mappers

import com.messenger.jaber.data.userInfo.entities.UserInfoRequest
import com.messenger.jaber.features.signup.domain.entities.NewAccount

internal class MappedUserData {
    fun mapAccountToUserInfoRequest(newAccount: NewAccount): UserInfoRequest {
        return UserInfoRequest(
            login = newAccount.login,
            firstName = newAccount.firstName,
            lastName = newAccount.lastName
        )
    }
}