package com.messenger.jaber.glue.signup.mappers

import com.messenger.jaber.data.firestore.userInfo.entities.UserInfoResponse
import com.messenger.jaber.features.signup.domain.entities.NewAccount

internal class MappedUserData {
    fun mapAccountToUserData(newAccount: NewAccount): UserInfoResponse {
        return UserInfoResponse(
            login = newAccount.login,
            firstName = newAccount.firstName,
            lastName = newAccount.lastName
        )
    }
}