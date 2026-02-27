package com.messenger.jaber.data

import com.messenger.jaber.data.userInfo.entities.UserInfoRequest

interface GetUserInfosDataRepository {
    suspend fun findUsersByEmail(email: String): UserInfoRequest?
}