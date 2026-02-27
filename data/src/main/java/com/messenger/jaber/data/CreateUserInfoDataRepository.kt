package com.messenger.jaber.data

import com.messenger.jaber.data.userInfo.entities.UserInfoRequest

interface CreateUserInfoDataRepository {
    suspend fun createUserInfo(userInfoRequest: UserInfoRequest)
}