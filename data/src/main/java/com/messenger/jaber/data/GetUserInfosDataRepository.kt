package com.messenger.jaber.data

import com.messenger.jaber.data.userInfo.entities.UserInfoResponse

interface GetUserInfosDataRepository {
    suspend fun findUsersByEmail(email: String): UserInfoResponse?
}