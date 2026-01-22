package com.messenger.jaber.glue.signup.mappers

import com.messenger.jaber.data.firestore.userData.entities.UserData
import com.messenger.jaber.features.signup.domain.entities.NewAccount

internal class MappedUserData() {
    fun mapAccountToUserData(newAccount: NewAccount): UserData.Default {
        return UserData.Default(
            login = newAccount.login,
            firstName = newAccount.firstName,
            lastName = newAccount.lastName
        )
    }
}